package com.example.datnguyen.movie.Controller;

import com.example.datnguyen.movie.DTO.Reponse.ApiResponse;
import com.example.datnguyen.movie.DTO.Request.UserCreationRequest;
import com.example.datnguyen.movie.Service.Impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserServiceImpl userService;
    @PostMapping
    ResponseEntity<?> createUser(@RequestBody UserCreationRequest request){
        userService.createUser(request);
        ApiResponse<?> apiResponse=ApiResponse.builder()
                .message("create user success")
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
