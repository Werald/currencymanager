package com.poodel.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Абстрактный класс для реализации потомков-команд в пакете
 * @see com.poodel.commands.commands_implementation
 */
public abstract class FatherOfCommands {

    /**
     * Функция, инициализирующая начало проверки и сравнения в классах-потомках.
     *
     * @param inCommand строка команда, переданная на исполнение в соответствующий сигнатуре класс из пакета
     * @see com.poodel.database_manager
     */
    public abstract void execute(String inCommand);

    /**
     * Функция, которая при перезаписи наследниками будет парсить команду пользователя с разделителями в виде пробелов
     *
     * @param inCommand введенная команда пользователем
     * @return массив строк, каждому члену которого соответствует 1 слово из команды (делимитатор - спэйсбар)
     */
    public abstract String[] getDataArrFromUI(String inCommand);

    /**
     * Функция, производящая сравнение на соотвествие передаваемой строки шаблону RegExp.
     *
     * @param inCommand целая команда, введенная пользователем
     * @param commandParser RegExp-шаблон "COMMAND_PARSER" правильного формата команды
     * @return true, если введенная команда соотвествует шаблону
     */
    protected boolean checkCommand(String inCommand, String commandParser) {
        Pattern pattern = Pattern.compile(commandParser);
        Matcher matcher = pattern.matcher(inCommand);
        return matcher.matches();
    }

    /**
     * Функция, сверяющая переданную строку-валюту на наличие аналога в Enum-e CurrencyType
     *
     * @param currencyStr проверяемая на соответствие формату валюта
     * @return true, если такое значение есть в enum-е CurrencyType
     */
    protected boolean isCurrencyTypeCorrect(String currencyStr) {
        boolean typeCorrect = false;
        for (CurrencyType ct : CurrencyType.values()) {
            if (ct.getAbbreviation().equals(currencyStr)) {
                typeCorrect = true;
            }
        }
        return typeCorrect;
    }
}
