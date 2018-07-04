package com.poodel;

import com.poodel.commands.*;
import com.poodel.commands.command_exceptions.CommandDoesNotExistExeption;

import java.util.Scanner;

public class Main {
    private static final String ERROR_MESSAGE = "Error!Command not found!\n Type \"help\" to get list of commands.";
    private HelpCommand helpCommand = new HelpCommand();
    private AddCommand addCommand = new AddCommand();
    private ListCommand listCommand = new ListCommand();
    private ClearCommand clearCommand = new ClearCommand();
    private TotalCommand totalCommand = new TotalCommand();


    public static void main (String[] args) {



        Scanner scanner = new Scanner(System.in);
        String enteredCommand = scanner.nextLine();
      //  System.out.println(enteredCommand);
        Main sd = new Main();
        sd.commandIdentification(enteredCommand);


    }
    private void commandIdentification(String enteredCommand) {

      //  System.out.println(enteredCommand);
        String[] commandSubStr = enteredCommand.split("\\s");

//
//        if ("add".equals(commandSubStr[0])) {
//            addCommand.execute(enteredCommand);
//        } else if ("help".equals(commandSubStr[0])) {
//            helpCommand.execute(enteredCommand);
//            }
        switch (commandSubStr[0]){
            case "add" : addCommand.execute(enteredCommand);
            break;
            case "help" : helpCommand.execute(enteredCommand);
            break;
            case "list" : listCommand.execute(enteredCommand);
            break;
            case "clear" : clearCommand.execute(enteredCommand);
            break;
            case "total" : totalCommand.execute(enteredCommand);
            break;

         }
    }
}

