package com.example.datnguyen.movie.Config;

import com.example.datnguyen.movie.DTO.Request.IntrospectRequest;
import com.example.datnguyen.movie.Exception.AppException;
import com.example.datnguyen.movie.Exception.ErrorCode;
import com.example.datnguyen.movie.Service.Impl.AuthenticationServiceImpl;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.secret_key}")
    private String PRIVATE_KEY;
    @Autowired
    private AuthenticationServiceImpl authenticationService;
    private NimbusJwtDecoder nimbusJwtDecoder=null;
    @Override
    public Jwt decode(String token) throws JwtException {
        IntrospectRequest request= IntrospectRequest.builder()
                .token(token)
                .build();
        var response=authenticationService.introspect(request);
        if(!response){
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
        if(Objects.isNull(nimbusJwtDecoder)){
            SecretKeySpec secretKeySpec=new SecretKeySpec(PRIVATE_KEY.getBytes(),"HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build(); //set nimbusJwtDecoder to decode token
        }
        return nimbusJwtDecoder.decode(token);
    }
}
