package com.example.demo.exceptions;

public class ReadFileException extends RuntimeException {
    public ReadFileException(String message, Exception exception) {
        super(message, exception);
    }
}
