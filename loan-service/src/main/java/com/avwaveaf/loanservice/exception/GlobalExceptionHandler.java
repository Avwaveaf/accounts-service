package com.avwaveaf.loanservice.exception;


import com.avwaveaf.loanservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles invalid method arguments; returns error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> fieldNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));


        // Builds error response with validation failure details
        ErrorResponseDTO res = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(errors.toString())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> globalException(Exception ex, WebRequest request) {
        // Builds error response with global exception details
        ErrorResponseDTO err = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(ex.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        // Builds error response with resource not found details
        ErrorResponseDTO err = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFound(ResourceAlreadyExistException ex, WebRequest request) {
        // Builds error response with resource not found details
        ErrorResponseDTO err = ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
