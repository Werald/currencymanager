package com.poodel.commands.commands_implementation;

import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.command_exceptions.WrongFormatOfCommandException;
import com.poodel.database_manager.TableTotal;

/**
 * Реализация парсинга и проверки команды "total"
 */
public class TotalCommand extends FatherOfCommands {

    /**
     * @param COMMAND_PARSER шаблон RegExp, хранящий формат поступаемой команды.
     */
    private static final String COMMAND_PARSER = "total\\s[A-Z]{3}";
    private static final String ERROR_MESSAGE = "\nWrong signature of command!\nUse following format for alignment to chosen currency: \n \"total CUR\"\n";

    public void execute(String inCommand)  {
        String [] parsedCommand = getDataArrFromUI(inCommand);
        try {
            if(checkCommand(inCommand, COMMAND_PARSER) && isCurrencyTypeCorrect(parsedCommand[1])) {
                TableTotal tableTotal = new TableTotal();
                System.out.println();
                tableTotal.countTotal(parsedCommand[1]);
            } else {
            throw new WrongFormatOfCommandException(ERROR_MESSAGE);
            }
        } catch (WrongFormatOfCommandException e){
            System.out.println(ERROR_MESSAGE);
//            System.out.println(e.getClass().getName() + "; " + e.getMessage());
        }
    }

    public String[] getDataArrFromUI(String inCommand) {
        return inCommand.split("\\s", 2);
    }
}
