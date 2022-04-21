package com.cards.game.handler;

import com.cards.game.models.exceptions.NotFoundResponse;
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
    public ResponseEntity handleException(NotFoundException exc) {
        log.info(exc.getMessage());
        NotFoundResponse e = new NotFoundResponse();

        e.setStatus(HttpStatus.NOT_FOUND.value());
        e.setMessage(exc.getMessage());
        e.setTimeStamp(new LocalDateTime());

        return new ResponseEntity(e, HttpStatus.NOT_FOUND);
    }
}