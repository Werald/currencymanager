package com.poodel.commands.commands_implementation;

import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.command_exceptions.WrongFormatOfCommandException;
import com.poodel.database_manager.TableList;

/**
 * Реализация парсинга и проверки команды "list"
 */
public class ListCommand extends FatherOfCommands {
    /**
     * @param COMMAND_PARSER шаблон RegExp, хранящий формат поступаемой команды.
     */
    private static final String COMMAND_PARSER = "list";
    private static final String ERROR_MESSAGE = "\nWrong signature of command!\nUse following format for displaying all expenses:\n \"list\"\n";

    public void execute(String inCommand) {
        try {
            if (checkCommand(inCommand, COMMAND_PARSER)){
                System.out.println();
                TableList.displayExpenses();
            } else {
                throw new WrongFormatOfCommandException(ERROR_MESSAGE);
            }
        } catch (WrongFormatOfCommandException e ){
            System.out.println(ERROR_MESSAGE);
//            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }
}
