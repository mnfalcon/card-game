package com.cards.game.handler;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.cards.game.models.exceptions.ExceptionResponse;
import com.cards.game.models.exceptions.NotFoundResponse;
import com.cards.game.services.exceptions.ImageRequiredException;
import com.cards.game.services.exceptions.NotFoundException;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity notFoundException(NotFoundException exc) {
        log.info(exc.getMessage());
        NotFoundResponse e = new NotFoundResponse();

        e.setStatus(HttpStatus.NOT_FOUND.value());
        e.setMessage(exc.getMessage());
        e.setTimeStamp(new LocalDateTime());

        return new ResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AmazonS3Exception.class})
    public ResponseEntity amazonS3Exception(AmazonS3Exception exc) {
        log.info(exc.getMessage());
        ExceptionResponse e = createExceptionResponse(exc.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ImageRequiredException.class})
    public ResponseEntity imageRequiredException(ImageRequiredException exc) {
        log.info(exc.getMessage());
        ExceptionResponse e = createExceptionResponse(exc.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        return new ResponseEntity(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ExceptionResponse createExceptionResponse(String message, HttpStatus status) {
        ExceptionResponse e = new ExceptionResponse();

        e.setStatus(status.value());
        e.setMessage(message);
        e.setTimeStamp(new LocalDateTime());

        return e;
    }

}
