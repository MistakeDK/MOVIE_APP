package com.example.datnguyen.movie.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    CATCH_ERROR(0,"Catch Exception failed"),
    MOVIE_NOT_EXIST(1000,"Movie not Exist"),
    USER_EXIST(2000,"User Exist");
    final int code;
    final String message;
}
