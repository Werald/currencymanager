package com.poodel.commands.commands_implementation;

import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.command_exceptions.WrongFormatOfCommandException;
import com.poodel.database_manager.TableInsert;


/**
 * Реализация парсинга и проверок команды "add"
 */
public class AddCommand extends FatherOfCommands {

    /**
     * @param COMMAND_PARSER - шаблон RegExp, хранящий формат поступаемой команды.
     */
    private static final String COMMAND_PARSER = "add\\s[12]\\d{3}[-](([0][1-9])|([1][0-2]))[-](([0][1-9])|([12][0-9])|([3][01]))\\s\\d+(\\.[\\d]{1,2})?\\s[A-Z]{3}\\s.{3,100}";
    private static final String ERROR_MESSAGE = "Wrong signature of command!\nUse following format for adding expense record: \n \"add yyyy-mm-dd xxxx CUR description\" ";

    public void execute(String inCommand) {
        String[] parsedCommand;
        parsedCommand = getDataArrFromUI(inCommand);
        try {
            if (checkCommand(inCommand, COMMAND_PARSER) && isCurrencyTypeCorrect(parsedCommand[3])) {
                TableInsert tableInsert = new TableInsert();
                tableInsert.addRecord(parsedCommand[1], parsedCommand[2], parsedCommand[3], parsedCommand[4]);
            } else {
            throw new WrongFormatOfCommandException(ERROR_MESSAGE);
            }
        } catch (WrongFormatOfCommandException e ){
//        System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return inCommand.split("\\s", 5);
    }



}





