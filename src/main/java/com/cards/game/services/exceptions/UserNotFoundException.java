package com.cards.game.services.exceptions;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("User");
    }
}
