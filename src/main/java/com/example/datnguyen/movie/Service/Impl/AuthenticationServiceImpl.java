package com.example.datnguyen.movie.Service.Impl;

import com.example.datnguyen.movie.DTO.Request.IntrospectRequest;
import com.example.datnguyen.movie.DTO.Request.LoginRequest;
import com.example.datnguyen.movie.Entity.User;
import com.example.datnguyen.movie.Exception.AppException;
import com.example.datnguyen.movie.Exception.ErrorCode;
import com.example.datnguyen.movie.Repository.UserRepository;
import com.example.datnguyen.movie.Service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    @Value("${jwt.time_valid}")
    @NonFinal
    long VALID_DURATION;
    @Value("${jwt.secret_key}")
    @NonFinal
    String PRIVATE_KEY;
    RedisServiceImpl redisService;
    @Override
    public Boolean introspect(IntrospectRequest request) {
        var token=request.getToken();
        boolean isValid=true;
        try {
            verifyToken(token);
        }catch (AppException | JOSEException | ParseException e){
            isValid=false;
        }
        return isValid;
    }

    @Override
    public String generateToken(User user) throws JOSEException {
        Date expiration=new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli());
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getId())
                .issueTime(new Date())
                .claim("scope",buildScope(user))
                .expirationTime(expiration)
                .build();
        Payload payload=new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject=new JWSObject(header,payload);
        jwsObject.sign(new MACSigner(PRIVATE_KEY.getBytes(StandardCharsets.UTF_8)));
        return jwsObject.serialize();
    }

    @Override
    public String authentication(LoginRequest request) throws JOSEException {
        PasswordEncoder encoder=new BCryptPasswordEncoder(10);
        User user= userRepository.findByGmail(request.getGmail())
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXIST));
        if(encoder.matches(request.getPassword(), user.getPassword())){
            return generateToken(user);
        }else {
            throw new AppException(ErrorCode.UN_AUTHENTICATED);
        }
    }
    @Override
    public String buildScope(User user) {
        return user.getRole().toString();
    }

    @Override
    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier=new MACVerifier(PRIVATE_KEY.getBytes(StandardCharsets.UTF_8));
        SignedJWT signedJWT=SignedJWT.parse(token);
        Date expiryTime=signedJWT.getJWTClaimsSet().getExpirationTime();
        if(expiryTime.before(Date.from(Instant.now()))||redisService.hasKey(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
        var verified=signedJWT.verify(verifier);
        var user=userRepository.findById(signedJWT.getJWTClaimsSet().getSubject())
                .orElseThrow(()->new AppException(ErrorCode.UN_AUTHENTICATED));
        return signedJWT;
    }

    @Override
    public void logout(String token,HttpServletResponse response) throws ParseException, JOSEException {
        if(token.isEmpty()){
            return;
        }
        SignedJWT signedJWT=verifyToken(token);
        redisService.saveKeyAndTTL(signedJWT.getJWTClaimsSet().getJWTID(),"INVALID_TOKEN",calculateTimeExpire(signedJWT));
        Cookie cookie=new Cookie("jwt",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    public long calculateTimeExpire(SignedJWT signedJWT) throws ParseException {
        Duration duration=Duration.between(Instant.now(),signedJWT.getJWTClaimsSet().getExpirationTime().toInstant());
        return duration.getSeconds();
    }
}
