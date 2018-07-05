package com.poodel.commands;

import com.poodel.database_manager.TableTotal;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TotalCommand extends AbsCommands {
    private static final String COMMAND_PARSER = "[t][o][t][a][l]\\s[A-Z]{3}";
    private static final String ERROR_MESSAGE = "No expenses found.";

    public void execute(String inCommand) {
        String [] parsedCommand = getDataArrFromUI(inCommand);
        try {

        if(checkCommand(inCommand, COMMAND_PARSER)) {
            TableTotal tableTotal = new TableTotal();
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            double result = tableTotal.getTotal(parsedCommand[1]);

            System.out.println(df.format(result));

        }

        } catch (Exception e){
            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return inCommand.split("\\s", 2);
    }
}
