package com.example.datnguyen.movie.DTO.Reponse;

import com.example.datnguyen.movie.Enum.CategoryMovie;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {
    String slugName;
    String name;
    CategoryMovie categoryMovie;
    String img;
    Double price;
    Boolean isActive;
}
