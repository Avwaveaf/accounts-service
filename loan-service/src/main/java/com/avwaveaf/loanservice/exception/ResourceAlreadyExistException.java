package com.avwaveaf.loanservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s with %s = %s Already Exist!", resourceName, fieldName, fieldValue));
    }
}