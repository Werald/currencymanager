package com.poodel.commands.commands_implementation;

import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.command_exceptions.WrongFormatOfCommandException;
import com.poodel.database_manager.TableList;

/**
 * Реализация парсинга и проверки команды "list"
 */
public class ListCommand extends FatherOfCommands {
    /**
     * @param COMMAND_PARSER - шаблон RegExp, хранящий формат поступаемой команды.
     */
    private static final String COMMAND_PARSER = "list";
    private static final String ERROR_MESSAGE = "Wrong signature of command!\nUse following format for displaying all expenses:\n \"list\"";

    public void execute(String inCommand) {
        try {
            if (checkCommand(inCommand, COMMAND_PARSER)){
                TableList tableList = new TableList();
                tableList.displayExpenses();
            } else {
                throw new WrongFormatOfCommandException(ERROR_MESSAGE);
            }
        } catch (WrongFormatOfCommandException e ){
//            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }

}
