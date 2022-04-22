package com.cards.game.services.exceptions;

import lombok.Data;

@Data
public class ImageRequiredException extends RuntimeException {

    public ImageRequiredException() {
        super("Tried to create a card without providing an image. An image is required.");
    }

    public ImageRequiredException(String message) {
        super(message);
    }
}
