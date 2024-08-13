package com.example.datnguyen.movie.Service;

import com.example.datnguyen.movie.DTO.Reponse.ApiResponse;
import com.example.datnguyen.movie.DTO.Request.IntrospectRequest;
import com.example.datnguyen.movie.DTO.Request.LoginRequest;
import com.example.datnguyen.movie.Entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;

public interface AuthenticationService {
    Boolean introspect(IntrospectRequest request);
    String generateToken(User user) throws JOSEException;
    String authentication(LoginRequest request) throws JOSEException;
    String buildScope(User user);
    SignedJWT verifyToken(String token) throws JOSEException, ParseException;

    void logout(String token,HttpServletResponse response) throws ParseException, JOSEException;
}
