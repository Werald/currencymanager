package com.poodel.commands;


import com.poodel.fixer_resourses.CurrencyType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class FatherOfCommands {

    public abstract void execute(String inCommand);
    public abstract String[] getDataArrFromUI(String inCommand);

    protected boolean checkCommand(String inCommand, String commandParser) {
        Pattern pattern = Pattern.compile(commandParser);
        Matcher matcher = pattern.matcher(inCommand);
        return matcher.matches();
    }

    protected boolean isCurrencyTypeCorrect(String currencyStr) {
        boolean typeIncorrect = false;

        for (CurrencyType ct : CurrencyType.values()) {
            if (ct.getAbbreviation().equals(currencyStr)) {
                typeIncorrect = true;
            }
        }
        return typeIncorrect;
    }


}
