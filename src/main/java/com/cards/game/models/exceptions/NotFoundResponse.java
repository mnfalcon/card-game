package com.cards.game.models.exceptions;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.LocalDateTime;

@Getter
@Setter
public class NotFoundResponse {

    private int status;
    private String message;
    private LocalDateTime timeStamp;

    @Override
    public String toString() {
        return "NotFoundResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
