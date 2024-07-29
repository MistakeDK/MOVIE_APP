package com.example.datnguyen.movie.Exception;

import com.example.datnguyen.movie.DTO.Reponse.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler({AppException.class})
    ResponseEntity<ErrorResponse> handleAppException(AppException exception){
        ErrorResponse errorResponse=ErrorResponse.builder()
                .code(exception.getErrorCode().code)
                .message(exception.getErrorCode().message)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler({Exception.class})
    ResponseEntity<ErrorResponse> handleException(Exception exception){
        ErrorResponse errorResponse=ErrorResponse.builder()
                .message(ErrorCode.CATCH_ERROR.message)
                .code(ErrorCode.CATCH_ERROR.code)
                .build();
        return ResponseEntity.internalServerError().body(errorResponse);
    }

}
