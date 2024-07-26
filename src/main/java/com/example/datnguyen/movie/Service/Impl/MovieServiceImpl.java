package com.example.datnguyen.movie.Service.Impl;

import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;
import com.example.datnguyen.movie.Entity.Movie;
import com.example.datnguyen.movie.Mapper.MovieMapper;
import com.example.datnguyen.movie.Repository.MovieRepository;
import com.example.datnguyen.movie.Service.Http.ClientService;
import com.example.datnguyen.movie.Service.MovieService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {
    ClientService service;
    MovieRepository repository;
    MovieMapper mapper;
    @Override
    public Object getListFeignClient(int page,String keyword) {
        if(Objects.isNull(keyword)){
            return service.getMovie(page);
        }
        return service.searchMovie(10,keyword);
    }

    @Override
    public void createMovie(MovieCreationRequest request) {
        Movie movie=mapper.toMovie(request);
        movie.setIsActive(true);
        movie.getEpisodes().forEach((episode)-> episode.setMovie(movie));
        repository.save(movie);
    }

}
