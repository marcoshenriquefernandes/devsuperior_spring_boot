package com.devsuperior.clientscrud.controllers.handlers;

import com.devsuperior.clientscrud.dto.errors.ErrorMessage;
import com.devsuperior.clientscrud.dto.errors.MessageList;
import com.devsuperior.clientscrud.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage error = new ErrorMessage(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        MessageList error = new MessageList(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());

        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            error.addMessage(field.getField(), field.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }
}
