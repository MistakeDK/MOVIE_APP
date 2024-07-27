package com.example.datnguyen.movie.Mapper;

import com.example.datnguyen.movie.DTO.Reponse.MovieResponse;
import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;
import com.example.datnguyen.movie.Entity.Movie;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

@Mapper(uses = EpisodeMapper.class)
public interface MovieMapper {
    Movie toMovie(MovieCreationRequest request);
    MovieResponse toMovieResponse(Movie movie);
}
