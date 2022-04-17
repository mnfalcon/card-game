package com.cards.game.services.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity) {
        super(entity + " with provided id not found");
    }
}
