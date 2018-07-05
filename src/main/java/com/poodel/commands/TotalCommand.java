package com.poodel.commands;

public class TotalCommand extends AbsCommands {
    private static final String COMMAND_PARSER = "[t][o][t][a][l]\\s[A-Z]{3}";
    private static final String ERROR_MESSAGE = "No expenses found.";

    public void execute(String inCommand) {

    }

    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }
}
