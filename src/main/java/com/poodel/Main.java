package com.poodel;

import com.poodel.commands.*;
import com.poodel.commands.command_exceptions.CommandDoesNotExistExeption;
import com.poodel.database_manager.TableClear;
import com.poodel.database_manager.TableCreate;
import com.poodel.database_manager.TableTotal;

import java.util.Scanner;

public class Main {
    private static final String ERROR_MESSAGE = "Error!Command not found!\n Type \"help\" to get list of commands.";
    private AddCommand addCommand = new AddCommand();
    private ListCommand listCommand = new ListCommand();
    private ClearCommand clearCommand = new ClearCommand();
    private TotalCommand totalCommand = new TotalCommand();
    private static final String START_MESSAGE = "\nList of commands:\n" +
            "- add yyyy-mm-dd xxxx CUR description  ---  used for create new expense (yyyy-mm-dd - expense date; xxxx - value of expense; CUR - currency type)\n" +
            "- list  ---  show the list of all expenses sorted by date\n" +
            "- clear yyyy-mm-dd  ---  clear all expenses from memory with specified date\n" +
            "- total CUR  ---  print total amount of all expenses specified in CUR currency type\n" +
            "- exit  ---  close application";


    public static void main (String[] args) {
        System.out.println(START_MESSAGE);
        TableCreate.createTable();
           do {
               Scanner scanner = new Scanner(System.in);
               String enteredCommand = scanner.nextLine();

               Main sd = new Main();
               sd.commandIdentification(enteredCommand);
           } while (true);
    }

    private void commandIdentification(String enteredCommand) {
        String[] commandSubStr = enteredCommand.split("\\s");
        switch (commandSubStr[0]){
            case "add" : addCommand.execute(enteredCommand);
            break;
            case "list" : listCommand.execute(enteredCommand);
            break;
            case "clear" : clearCommand.execute(enteredCommand);
            break;
            case "total" : totalCommand.execute(enteredCommand);
            break;
            case "exit" : System.exit(0);
            break;

         }
    }
}

