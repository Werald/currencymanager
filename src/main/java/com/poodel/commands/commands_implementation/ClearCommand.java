package com.poodel.commands.commands_implementation;

import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.command_exceptions.WrongFormatOfCommandException;
import com.poodel.database_manager.TableClear;

/**
 * Реализация парсинга и проверки команды "clear"
 */
public class ClearCommand extends FatherOfCommands {
    /**
     * @param COMMAND_PARSER - шаблон RegExp, хранящий формат поступаемой команды.
     */
    private static final String COMMAND_PARSER = "clear\\s[12]\\d{3}[-](([0][1-9])|([1][0-2]))[-](([0][1-9])|([12][0-9])|([3][01]))";
    private static final String ERROR_MESSAGE = "Wrong signature of command!\nUse following format for deleting expense record: \n \"clear yyyy-mm-dd\"";

    public void execute(String inCommand) {
        String [] parsedCommand;
        try {
            if (checkCommand(inCommand, COMMAND_PARSER)){
                parsedCommand = getDataArrFromUI(inCommand);
                TableClear tableClear = new TableClear();
                tableClear.deleteRecord(parsedCommand[1]);
            } else {
                throw new WrongFormatOfCommandException(ERROR_MESSAGE);
            }
        } catch (WrongFormatOfCommandException e){
//            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return inCommand.split("\\s", 2);
    }
}
