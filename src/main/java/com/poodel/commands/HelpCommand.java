package com.poodel.commands;

import com.poodel.commands.command_exceptions.WrongCommandFormatException;

public class HelpCommand extends AbsCommands{

    private static final String ERROR_MSG = "error in Help command occurred";
    private static final String COMMAND_PARSER = "help";
    private static final String HELP = "\nList of commands:\n" +
            "- add yyyy-mm-dd xxxx CUR description  ---  used for create new expense (yyyy-mm-dd - expense date; xxxx - value of expense; CUR - currency type)\n" +
            "- list  ---  show the list of all expenses sorted by date\n" +
            "- clear yyyy-mm-dd  ---  clear all expenses from memory with specified date\n" +
            "- total CUR  ---  print total amount of all expenses specified in CUR currency type\n" +
            "- exit  ---  close application";


//    Exception e = new Exception("Error in Help");
    public void execute(String inCommand) {

        try {
            if(!checkCommand(inCommand, COMMAND_PARSER)){
                throw new WrongCommandFormatException(ERROR_MSG);
            }
            System.out.println(HELP);
        }catch (WrongCommandFormatException ie) {

            System.out.println(ERROR_MSG);
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return inCommand.split("\\s", 0);
    }
}
