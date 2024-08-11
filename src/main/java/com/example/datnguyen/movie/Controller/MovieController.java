package com.example.datnguyen.movie.Controller;

import com.example.datnguyen.movie.DTO.Reponse.ApiResponse;
import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;
import com.example.datnguyen.movie.Enum.CategoryMovie;
import com.example.datnguyen.movie.Exception.AppException;
import com.example.datnguyen.movie.Exception.ErrorCode;
import com.example.datnguyen.movie.Service.Impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class MovieController {
    MovieServiceImpl movieService;
    @GetMapping()
    ResponseEntity<?> getListFeignClient(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(required = false) String keyword){
         var list= movieService.getListFeignClient(page,keyword);
         ApiResponse<?> apiResponse=ApiResponse.builder().result(list).build();
         return ResponseEntity.ok().body(apiResponse);
    }
    @PostMapping()
    ResponseEntity<?> createMovie(@RequestBody MovieCreationRequest request){
        movieService.createMovie(request);
        ApiResponse<?> apiResponse=ApiResponse.builder().message("Create Success").build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @GetMapping("/list")
    ResponseEntity<?> getList(Pageable pageable,
                              @RequestParam(required = false) String keyword,
                              @RequestParam(required = false,defaultValue = "ALL") String category,
                              HttpServletRequest request) {
        var list=movieService.getList(pageable,keyword,category);
        ApiResponse<?> apiResponse=ApiResponse.builder().result(list).build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @GetMapping("/getById/{id}")
    ResponseEntity<?> getById(@PathVariable String id){
        var result=movieService.getById(id);
        ApiResponse<?> apiResponse=ApiResponse.builder()
                .result(result)
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
