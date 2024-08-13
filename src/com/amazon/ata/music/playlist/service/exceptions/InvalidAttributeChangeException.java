package com.amazon.ata.music.playlist.service.exceptions;

public class InvalidAttributeChangeException extends InvalidAttributeException{
    private static final long serialVersionUID = 4087296302912214259L;

    public InvalidAttributeChangeException() {super();}

    public InvalidAttributeChangeException(String message) {
        super(message);
    }

    public InvalidAttributeChangeException(Throwable cause) {
        super(cause);
    }
    public InvalidAttributeChangeException(String message , Throwable cause) {
        super(message, cause);
    }
}
