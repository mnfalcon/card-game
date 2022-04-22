package com.cards.game.services.exceptions;

public class InvalidAttackMoveException extends RuntimeException {
    public InvalidAttackMoveException() {
        super("Invalid attack move.");
    }
}
