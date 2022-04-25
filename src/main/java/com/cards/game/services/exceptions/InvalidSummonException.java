package com.cards.game.services.exceptions;

public class InvalidSummonException extends RuntimeException {
    public InvalidSummonException() {
        super("Invalid summoning.");
    }
}
