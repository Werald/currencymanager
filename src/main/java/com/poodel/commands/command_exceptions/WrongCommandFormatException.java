package com.poodel.commands.command_exceptions;

public class WrongCommandFormatException extends Exception {
    public WrongCommandFormatException() {
        super();
    }

    public WrongCommandFormatException(String errorMessage) {
        super(errorMessage);
    }
}
