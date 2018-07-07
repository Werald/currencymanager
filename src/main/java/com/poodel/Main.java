package com.poodel;

import com.poodel.commands.commands_implementation.AddCommand;
import com.poodel.commands.commands_implementation.ClearCommand;
import com.poodel.commands.commands_implementation.ListCommand;
import com.poodel.commands.commands_implementation.TotalCommand;
import com.poodel.database_manager.TableCreate;

import java.lang.invoke.WrongMethodTypeException;
import java.util.Scanner;

/**
 * <b>Personal Expenses Manager</b>.
 * @author Alex Poodel
 * @version 1.488
 */
public class Main {
    private static final String ERROR_MESSAGE = "Error! Command not found!";
    private AddCommand addCommand = new AddCommand();
    private ListCommand listCommand = new ListCommand();
    private ClearCommand clearCommand = new ClearCommand();
    private TotalCommand totalCommand = new TotalCommand();
    private static final String START_MESSAGE = "\nList of commands:\n" +
            "- add yyyy-mm-dd xxxx CUR description  -  used for create new expense (yyyy-mm-dd - expense date; xxxx - value of expense; CUR - currency type)\n" +
            "- list  -  show the list of all expenses sorted by date\n" +
            "- clear yyyy-mm-dd  -  clear all expenses from memory with specified date\n" +
            "- total CUR  -  print total amount of all expenses specified in CUR currency type\n" +
            "- exit  -  close application";


    /**
     * Точка старта программы.
     * @param args - обязательный параметр main()
     */
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


    /**
     * Первый этап парсинга команд пользователя. В случае совпадения по первому слову
     * перенаправляется на соответсвующий команде класс для проверок.
     * @param enteredCommand - текст, введённый пользователем.
     */
    private void commandIdentification(String enteredCommand) {

        String[] commandSubStr = enteredCommand.split("\\s");

        try{
        switch (commandSubStr[0]) {
            case "add":
                addCommand.execute(enteredCommand);
                break;
            case "list":
                listCommand.execute(enteredCommand);
                break;
            case "clear":
                clearCommand.execute(enteredCommand);
                break;
            case "total":
                totalCommand.execute(enteredCommand);
                break;
            case "exit":
                System.exit(0);
                break;
            default: throw new WrongMethodTypeException(ERROR_MESSAGE);
        }
         } catch (WrongMethodTypeException e){
            System.out.println(START_MESSAGE);
        }
    }
}

