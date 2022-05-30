package com.sahabt.project.controller.handler;

import com.sahabt.project.dto.error.ErrorResponse;
import com.sahabt.project.exception.ProjectAlreadyExistException;
import com.sahabt.project.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerErrorHandler {
    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProjectNotFoundException(ProjectNotFoundException e) {
        return new ErrorResponse("Customer Rest API", e.getMessage());
    }

    @ExceptionHandler(ProjectAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProjectAlreadyExistException(ProjectAlreadyExistException e) {
        return new ErrorResponse("Customer Rest API", e.getMessage());
    }
}
