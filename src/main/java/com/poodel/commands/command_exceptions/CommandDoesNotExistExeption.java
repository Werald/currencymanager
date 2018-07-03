package com.poodel.commands.command_exceptions;

public class CommandDoesNotExistExeption extends Exception {
    public CommandDoesNotExistExeption(){
        super();
    }
    public CommandDoesNotExistExeption(String errorMsg) {
        super(errorMsg);
    }
}
