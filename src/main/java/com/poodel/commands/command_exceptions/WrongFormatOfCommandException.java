package com.poodel.commands.command_exceptions;

/**
 * Класс-Ислючение, инстанциируемый для обработки исключений, возникающих в классах
 * com.poodel.commands.commands_implementation
 */
public class WrongFormatOfCommandException extends Exception {

    /**
     * Конструктор, принимающий строку с описанием ошибки и
     * вызывающий конструктор родителя с переданным сообщением об ошибке.
     *
     * @param errorMessage - Строка, сообщение об ошибке, передаваемое генератору исключений.
     */
    public WrongFormatOfCommandException(String errorMessage) {
        super(errorMessage);
    }
}
