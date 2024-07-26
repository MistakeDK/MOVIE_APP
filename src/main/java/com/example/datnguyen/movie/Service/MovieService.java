package com.example.datnguyen.movie.Service;

import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;

public interface MovieService {
    Object getListFeignClient(int page,String keyword);

    void createMovie(MovieCreationRequest request);
}
