package com.employee.management.exception;

import com.employee.management.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

//	معالجة الأخطاء مركزيًا
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handNotFoundException(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(exception.getMessage(), null));
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse> handEmailException(DuplicateEmailException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse(exception.getMessage(), null));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handException(Exception exception){
        logger.error("Unexpected error occurred",exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("Internal server error", null));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handValidException(MethodArgumentNotValidException exception){
        List<String> errors=new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(errors.toString(),null));
    }
}
