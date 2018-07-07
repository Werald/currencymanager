package com.poodel.commands;

import com.poodel.commands.command_exceptions.WrongCommandFormatException;
import com.poodel.database_manager.TableClear;

public class ClearCommand extends FatherOfCommands {
    private static final String COMMAND_PARSER = "clear\\s[12]\\d{3}[-](([0][1-9])|([1][0-2]))[-](([0][1-9])|([12][0-9])|([3][01]))";
    private static final String ERROR_MESSAGE = "Error during execution.";

    public void execute(String inCommand) {
        String [] parsedCommand;

        try {

            if (checkCommand(inCommand, COMMAND_PARSER)){
                parsedCommand = getDataArrFromUI(inCommand);
                TableClear tableClear = new TableClear();
                tableClear.deleteRecord(parsedCommand[1]);
            } else {
                throw new WrongCommandFormatException(ERROR_MESSAGE);
            }
        } catch (Exception e ){
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }



    public String[] getDataArrFromUI(String inCommand) {
        return inCommand.split("\\s", 2);
    }
}
