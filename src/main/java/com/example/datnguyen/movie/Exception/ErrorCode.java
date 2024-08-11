package com.example.datnguyen.movie.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    CATCH_ERROR(0,"Catch Exception failed"),
    MOVIE_NOT_EXIST(1000,"Movie not Exist"),
    USER_EXIST(2000,"User Exist"),
    USER_NOT_EXIST(2001,"User not Exist"),
    UN_AUTHENTICATED(3000,"Unauthenticated"),
    UN_AUTHORIZATION(3001,"don't have permission"),
    TOKEN_INVALID(3002,"Token invalid");
    final int code;
    final String message;
}
