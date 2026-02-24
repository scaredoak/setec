package com.setec.crud.infra;

import com.setec.crud.exceptions.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(ResponseStatusException ex, HttpServletRequest req) {
        var statusCode = ex.getStatusCode();
        var reason = ex.getReason();
        var response = new ErrorResponse(
                statusCode.value(),
                reason,
                req.getRequestURI(),
                java.time.Instant.now().toString()
        );

        return ResponseEntity.status(statusCode.value()).body(response);
    }
}

