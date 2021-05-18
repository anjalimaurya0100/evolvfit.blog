package com.EvolvFit.Blog.controller;

import com.EvolvFit.Blog.dto.ErrorMessage;
import com.EvolvFit.Blog.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorMessage ResourceNotFoundHandler(ResourceNotFoundException ex) {
        ErrorMessage em = new ErrorMessage();
        em.setCode(ex.getClass().getName());
        em.setMessage(ex.getMessage());
        return em;
    }
}
