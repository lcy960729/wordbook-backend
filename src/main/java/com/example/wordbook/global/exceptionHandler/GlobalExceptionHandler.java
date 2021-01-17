package com.example.wordbook.global.exceptionHandler;

import com.example.wordbook.global.dto.ErrorResponse;
import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationExceptionHandle(ConstraintViolationException e){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> methodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException e){
        ErrorResponse errorResponse = ErrorResponse.of(e);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> httpRequestMethodNotSupportedExceptionHandle(HttpRequestMethodNotSupportedException e){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> accessDeniedExceptionHandle(AccessDeniedException e){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> businessExceptionHandle(BusinessException e){
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandle(Exception e){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
