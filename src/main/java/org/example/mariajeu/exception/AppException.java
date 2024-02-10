package org.example.mariajeu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{
    protected ErrorCode errorCode;
    private String message;
}
