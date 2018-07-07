package com.poodel.commands;

import com.poodel.commands.command_exceptions.WrongCommandFormatException;
import com.poodel.database_manager.TableList;

public class ListCommand extends FatherOfCommands {
    private static final String COMMAND_PARSER = "list";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"list\" ?";

    public void execute(String inCommand) {
        try {
            if (checkCommand(inCommand, COMMAND_PARSER)){

                TableList tableList = new TableList();
                tableList.displayExpenses();
            } else {
                throw new WrongCommandFormatException(ERROR_MESSAGE);
            }
        } catch (Exception e ){
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }

}
