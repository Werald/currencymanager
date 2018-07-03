package com.poodel.commands;

public class AddCommand extends AbsCommands {

    private static final String COMMAND_PARSER = "add\\s[12]\\d{3}[-](([0][1-9])|([1][0-2]))[-](([0][1-9])|([12][0-9])|([3][01]))\\s\\d+(\\.[\\d]{1,2})?\\s[A-Z]{3}\\s.{3,100}";


    public void execute(String inCommand) {

    }

    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }
}
