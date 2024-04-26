package ru.neoflex.flowershop.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.neoflex.flowershop.error.ErrorMessage;
import ru.neoflex.flowershop.error.ErrorResponse;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ExceptionMessengerHandler {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> onConstraintValidationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(error -> new ErrorMessage(error.getPropertyPath().toString(), error.getMessage())).toList();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorMessage(error.getField(), error.getDefaultMessage())).toList();
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse onEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Entity not found", e.getMessage());
    }


}
