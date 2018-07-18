package com.poodel;

import com.poodel.commands.CurrencyType;
import com.poodel.commands.commands_implementation.AddCommand;
import com.poodel.commands.commands_implementation.ClearCommand;
import com.poodel.commands.commands_implementation.ListCommand;
import com.poodel.commands.commands_implementation.TotalCommand;
import com.poodel.database_manager.TableCreate;

import java.lang.invoke.WrongMethodTypeException;
import java.util.Scanner;


/**
 * <b>Expenses Manager</b>.
 * Provides managing of personal expenses using basic command-line operations (add, list, clear, total).
 * @author Alex Che Poodel
 * @author chealexpud@gmail.com
 * @version 0.9
 */
public class ExpensesManager {
    private static final String ERROR_MESSAGE = "Error! Command not found!";
    private static final String START_MESSAGE = "\n\tList of supported commands:\n" +
            "- add yyyy-mm-dd xxxx CUR description  -  add expense \n" +
            "- list  - show all expenses\n" +
            "- clear yyyy-mm-dd  -  delete all expenses on specified date\n" +
            "- total CUR  -  display amount of all expenses, fetched to currency\n" +
            "- curlist  - show all available currencies\n" +
            "- exit  -  finish application\n\n" +
            " \t List of used shortcuts: \n" +
            "'yyyy-mm-dd' - (2018-07-08) date format \n" +
            "'xxxx' - (14), amount of expense \n" +
            "'CUR' - (USD) currency \n" +
            "'description' - (Yogurt) expense description \n";


    private AddCommand addCommand = new AddCommand();
    private ListCommand listCommand = new ListCommand();
    private ClearCommand clearCommand = new ClearCommand();
    private TotalCommand totalCommand = new TotalCommand();

    /**
     * Entry point to class and application.
     * @param args array of string arguments
     */
    public static void main (String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        System.out.println(START_MESSAGE);
        TableCreate.createTable();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String enteredCommand = scanner.nextLine();

            ExpensesManager sd = new ExpensesManager();
            sd.commandIdentification(enteredCommand);
        }
    }


    /**
     * Первый этап парсинга команд пользователя. В случае совпадения по первому слову
     * перенаправляется на соответсвующий команде класс для проверок.
     * @param enteredCommand текст, введённый пользователем.
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
            case "curlist":
                int i=0;
                for (CurrencyType currencyType: CurrencyType.values()) {
                    System.out.print(currencyType + "\t");
                    i++;
                    if (i%11==0){
                        System.out.println();
                    }
                }
                break;
            default: throw new WrongMethodTypeException(ERROR_MESSAGE);
        }
         } catch (WrongMethodTypeException e){
            System.out.println(ERROR_MESSAGE + "\n" + START_MESSAGE);
        }
    }
}

