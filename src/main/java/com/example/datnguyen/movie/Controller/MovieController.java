package com.example.datnguyen.movie.Controller;

import com.example.datnguyen.movie.DTO.Reponse.ApiResponse;
import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;
import com.example.datnguyen.movie.Service.Impl.MovieServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
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
}
