package com.setec.crud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp;
    private String error;
    private int status;
    private String message;
    private String path;
}
