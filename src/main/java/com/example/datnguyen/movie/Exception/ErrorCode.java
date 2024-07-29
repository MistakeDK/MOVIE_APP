package com.example.datnguyen.movie.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    CATCH_ERROR(0,"Catch Exception failed");
    final int code;
    final String message;
}
