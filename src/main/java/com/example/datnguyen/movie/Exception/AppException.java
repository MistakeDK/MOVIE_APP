package com.example.datnguyen.movie.Exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class AppException extends RuntimeException {
    ErrorCode errorCode;
}
