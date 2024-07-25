package com.example.datnguyen.movie.DTO.Reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationResponse <T> {
    Long totalItems;
    Long totalItemPerPage;
    Long currentPage;
    T result;
}
