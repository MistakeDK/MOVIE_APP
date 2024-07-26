package com.example.datnguyen.movie.DTO.Request;

import com.example.datnguyen.movie.Enum.CategoryMovie;
import com.example.datnguyen.movie.Enum.TypeMovie;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieCreationRequest {
    @NonNull
    String slugName;
    @NonNull
    String name;
    @NonNull
    CategoryMovie categoryMovie;
    @NonNull
    String img;
    @NonNull
    Double price;
    @NonNull
    TypeMovie typeMovie;
    @NonNull
    Set<EpisodeCreationRequest> episodes;
}
