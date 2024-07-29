package com.example.datnguyen.movie.Service;

import com.example.datnguyen.movie.DTO.Reponse.MovieResponse;
import com.example.datnguyen.movie.DTO.Reponse.PaginationResponse;
import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Object getListFeignClient(int page,String keyword);

    void createMovie(MovieCreationRequest request);


    PaginationResponse<?> getList(Pageable pageable, String keyword,String category) throws Exception;
}
