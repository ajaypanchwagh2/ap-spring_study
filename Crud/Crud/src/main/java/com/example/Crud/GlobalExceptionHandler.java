package com.example.Crud;

import com.example.Crud.ErrorResponse;
import com.example.Crud.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class) // → 404
    public ResponseEntity<ErrorResponse> handleNotFound(PersonNotFoundException ex, jakarta.servlet.http.HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(), 404, "Not Found", ex.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(InvalidInputException.class) // → 400
    public ResponseEntity<ErrorResponse> handleBadRequest(InvalidInputException ex, jakarta.servlet.http.HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", ex.getMessage(), req.getRequestURI()));
    }

    // Bean validation errors → 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, jakarta.servlet.http.HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", msg, req.getRequestURI()));
    }

    // Fallback → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, jakarta.servlet.http.HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(LocalDateTime.now(), 500, "Internal Server Error", ex.getMessage(), req.getRequestURI()));
    }
}
