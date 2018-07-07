package com.poodel.commands;

import com.poodel.database_manager.TableTotal;

public class TotalCommand extends FatherOfCommands {
    private static final String COMMAND_PARSER = "[t][o][t][a][l]\\s[A-Z]{3}";
    private static final String ERROR_MESSAGE = "No expenses found.";

    public void execute(String inCommand) {
        String [] parsedCommand = getDataArrFromUI(inCommand);
        try {

        if(checkCommand(inCommand, COMMAND_PARSER)) {
            TableTotal tableTotal = new TableTotal();
            tableTotal.getTotal(parsedCommand[1]);
        }

        } catch (Exception e){
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return inCommand.split("\\s", 2);
    }
}
