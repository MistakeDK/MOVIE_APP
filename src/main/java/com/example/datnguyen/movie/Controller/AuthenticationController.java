package com.example.datnguyen.movie.Controller;

import com.example.datnguyen.movie.DTO.Reponse.ApiResponse;
import com.example.datnguyen.movie.DTO.Request.IntrospectRequest;
import com.example.datnguyen.movie.DTO.Request.LoginRequest;
import com.example.datnguyen.movie.Service.Impl.AuthenticationServiceImpl;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationServiceImpl authenticationService;
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) throws JOSEException {
        ApiResponse<?> apiResponse=ApiResponse.builder()
                .result("Login success")
                .build();
        Cookie cookie=new Cookie("jwt", authenticationService.authentication(request));
        response.addCookie(cookie);
        return ResponseEntity.ok().body(apiResponse);
    }
    @PostMapping("/logout")
    ResponseEntity<?> logout(HttpServletRequest request,HttpServletResponse response){
        authenticationService.logout(request,response);
        ApiResponse<?> apiResponse=ApiResponse.builder()
                .message("Log out success")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @PostMapping("/introspect")
    ResponseEntity<?> introspect(@RequestBody IntrospectRequest request){
        Boolean isAuth=authenticationService.introspect(request);
        ApiResponse<?> apiResponse=ApiResponse.builder()
                .message(isAuth.toString())
                .build();
        return  ResponseEntity.ok(apiResponse);
    }

}
