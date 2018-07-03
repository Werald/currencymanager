package com.poodel;

import com.poodel.commands.HelpCommand;

import java.util.Scanner;

public class Main {
    private static final String ERROR_MESSAGE = "Error!Command not found!\n Type \"help\" to get list of commands.";
    HelpCommand helpCommand = new HelpCommand();

    public static void main (String[] args) {



        Scanner scanner = new Scanner(System.in);
        String enteredCommand = scanner.next();
        Main sd = new Main();
        sd.commandIdentification(enteredCommand);


    }
    public void commandIdentification(String enteredCommand) {

//        try {
//            if (enteredCommand.length() < 3) {
//                throw new CommandDoesNotExistExeption(ERROR_MESSAGE);
//            }
//
//         String commandSubStr = enteredCommand.substring(0, 3);

        String commandSubStr = enteredCommand.substring(0, 3);


        //                case "add":
        //                    addCommand.execute(enteredCommand);
        //                    break;
        //                case "list":
        //                    listCommand.execute(enteredCommand);
        //                    break;
        //                case "clear":
        //                    clearCommand.execute(enteredCommand);
        //                    break;
        //                case "total":
        //                    totalCommand.execute(enteredCommand);
        //                    break;
        if ("hel".equals(commandSubStr)) {
            helpCommand.execute(enteredCommand);

        } else if ("exi".equals(commandSubStr)) {
//                default:
//                    throw new CommandDoesNotExistExeption(ERROR_MESSAGE);
        }
//        } catch (CommandDoesNotExistExeption e) {
//            System.out.println(ERROR_MESSAGE);
//        }
    }
}
