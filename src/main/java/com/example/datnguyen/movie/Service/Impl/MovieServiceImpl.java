package com.example.datnguyen.movie.Service.Impl;

import com.example.datnguyen.movie.DTO.Reponse.MovieDetailResponse;
import com.example.datnguyen.movie.DTO.Reponse.MovieResponse;
import com.example.datnguyen.movie.DTO.Reponse.PaginationResponse;
import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;
import com.example.datnguyen.movie.Entity.Episode;
import com.example.datnguyen.movie.Entity.Movie;
import com.example.datnguyen.movie.Exception.AppException;
import com.example.datnguyen.movie.Exception.ErrorCode;
import com.example.datnguyen.movie.Mapper.EpisodeMapper;
import com.example.datnguyen.movie.Mapper.MovieMapper;
import com.example.datnguyen.movie.Repository.EpisodeRepository;
import com.example.datnguyen.movie.Repository.MovieRepository;
import com.example.datnguyen.movie.Service.Http.ClientService;
import com.example.datnguyen.movie.Service.MovieService;
import com.example.datnguyen.movie.Specifications.MovieSpecifications;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {
    ClientService service;
    MovieRepository repository;
    EpisodeRepository episodeRepository;
    MovieMapper mapper;
    EpisodeMapper episodeMapper;
    @Override
    public Object getListFeignClient(int page,String keyword) {
        if(Objects.isNull(keyword)){
            return service.getMovie(page);
        }
        return service.searchMovie(10,keyword);
    }

    @Transactional
    @Override
    public void createMovie(MovieCreationRequest request) {
        Movie movie=mapper.toMovie(request);
        movie.setIsActive(true);
        movie.getEpisodes().forEach((episode)-> episode.setMovie(movie));
        repository.save(movie);
    }

    @Override
    public PaginationResponse<?> getList(Pageable pageable, String keyword,String category) {
        Page<Movie> list=null;
        list=repository.findAll(Specification.where(MovieSpecifications.hasCategory(category)
                        .and(MovieSpecifications.hasName(keyword))
                        .and(MovieSpecifications.hasIsActive("true"))),
                        pageable);
        return PaginationResponse.builder()
                .currentPage((long) list.getPageable().getPageNumber())
                .totalItems(list.getTotalElements())
                .totalItemPerPage((long) list.getPageable().getPageSize())
                .result(list.getContent().stream().map(mapper::toMovieResponse).toList())
                .build();
    }

    @Override
    public MovieDetailResponse getById(String id) {
        Movie movie=repository.findById(id).orElseThrow(()-> new AppException(ErrorCode.MOVIE_NOT_EXIST));
        List<Episode> episodes= episodeRepository.findByMovie_IdOrderByNameAsc(id);
        return MovieDetailResponse.builder()
                .episodes(episodes.stream().map(episodeMapper::toEpisodeResponse).toList())
                .movie(mapper.toMovieResponse(movie))
                .build();
    }
}
