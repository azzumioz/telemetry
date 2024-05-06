package ru.lightapps.telemetry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.lightapps.telemetry.util.exception.ErrorInfo;
import ru.lightapps.telemetry.util.exception.NotFoundException;


import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo notFound(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), e.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return new ErrorInfo(req.getRequestURL(), e.getLocalizedMessage());
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorInfo illegalArgument(HttpServletRequest req, IllegalArgumentException e) {
        return new ErrorInfo(req.getRequestURL(), e.getLocalizedMessage());
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorInfo throwException(HttpServletRequest req, MethodArgumentTypeMismatchException e) {

        return new ErrorInfo(req.getRequestURL(), e.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorInfo throwException(HttpServletRequest req, EmptyResultDataAccessException e) {
        String errorMessage = e.getMessage();
        String toReplace = e.getLocalizedMessage().substring(2, errorMessage.lastIndexOf('.') + 1);
        return new ErrorInfo(req.getRequestURL(), errorMessage.replaceAll(toReplace, " "));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorInfo nullEntity(HttpServletRequest req, NullPointerException e) {
        return new ErrorInfo(req.getRequestURL(), e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), e.getLocalizedMessage());
    }

}
