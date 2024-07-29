package com.example.datnguyen.movie.Mapper;

import com.example.datnguyen.movie.DTO.Reponse.EpisodeResponse;
import com.example.datnguyen.movie.DTO.Request.EpisodeCreationRequest;
import com.example.datnguyen.movie.DTO.Request.MovieCreationRequest;
import com.example.datnguyen.movie.Entity.Episode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper()
public interface EpisodeMapper {
    Episode toEpisode(EpisodeCreationRequest request);
    EpisodeResponse toEpisodeResponse(Episode episode);
}
