package com.poodel.commands;

import com.poodel.commands.command_exceptions.WrongCommandFormatException;
import com.poodel.database_manager.TableCreate;
import com.poodel.database_manager.TableInsert;

public class AddCommand extends AbsCommands {

    private static final String COMMAND_PARSER = "add\\s[12]\\d{3}[-](([0][1-9])|([1][0-2]))[-](([0][1-9])|([12][0-9])|([3][01]))\\s\\d+(\\.[\\d]{1,2})?\\s[A-Z]{3}\\s.{3,100}";

    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"add yyyy-mm-dd xxxx CUR description\" ?";

    public void execute(String inCommand) {
        String[] parsedCommand;
        parsedCommand = getDataArrFromUI(inCommand);
//       System.out.println(parsedCommand[1] + parsedCommand[2] + parsedCommand[3] + parsedCommand[4]);

    try {

        if (checkCommand(inCommand, COMMAND_PARSER) && isCurrencyTypeCorrect(parsedCommand[3])){

            TableInsert tableInsert = new TableInsert();
            tableInsert.addRecord(parsedCommand[1], parsedCommand[2], parsedCommand[3], parsedCommand[4]);
        } else {
            throw new  WrongCommandFormatException(ERROR_MESSAGE);
        }
    } catch (Exception e ){
        System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }
    public String[] getDataArrFromUI(String inCommand) {

        return inCommand.split("\\s", 5);
    }

//    public static void main(String sd[]){
//        AddCommand addCommand = new AddCommand();
//
//        String [] sds = addCommand.getDataArrFromUI("add 2017-04-25 12 USD Jogurt");
//
//        for (String qwe: sds
//             ) {
//            System.out.println(qwe);
//
//        }
//     System.out.println(addCommand.checkCommand("add 2017-04-25 12 USD Jogurt", COMMAND_PARSER));
//
//        TableInsert tableInsert = new TableInsert();
//
//        System.out.println(addCommand.isCurrencyTypeCorrect("USD"));
//        tableInsert.addRecord(sds[1], sds[2], sds[3], sds[4]);
//
//
//    }

}





