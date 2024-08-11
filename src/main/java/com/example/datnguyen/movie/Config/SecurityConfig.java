package com.example.datnguyen.movie.Config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig  {
    @Value("${jwt.secret_key}")
    private String PRIVATE_KEY;
    @Autowired
    private CustomJwtDecoder customJwtDecoder;
    private final HttpMethod[] PUBLIC_METHOD={
            HttpMethod.POST,HttpMethod.PATCH,HttpMethod.GET,HttpMethod.PUT,HttpMethod.DELETE
    };
    private final String[] PUBLIC_ENDPOINT={
            "/authentication/**",
            "/users/**",
            "/movies/**"
    };
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request->
                        request.requestMatchers(PUBLIC_ENDPOINT).permitAll().anyRequest().authenticated());
        http.oauth2ResourceServer(oauth2->oauth2.jwt(jwtConfigurer ->
                jwtConfigurer.
                        jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .bearerTokenResolver(cookieBearerTokenResolver())
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));
        return http.build();
    }
    @Bean
    BearerTokenResolver cookieBearerTokenResolver(){
        return new BearerTokenResolver() {
            @Override
            public String resolve(HttpServletRequest request) {
                log.info(Arrays.toString(request.getCookies()));
                if(request.getCookies()!=null){
                    for (Cookie cookie:request.getCookies()){
                        if("jwt".equals(cookie.getName())){
                            return cookie.getValue();
                        }
                    }
                }
                return null;
            }
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("scope");
        JwtAuthenticationConverter jwtAuthenticationConverter=new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
