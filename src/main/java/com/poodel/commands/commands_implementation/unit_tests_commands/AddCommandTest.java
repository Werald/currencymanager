import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.commands_implementation.AddCommand;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit-test для щаблона команды add и контроллера аббревиатур валют
 * @see AddCommand
 */
public class AddCommandTest extends FatherOfCommands  {

    private static final String COMMAND_PARSER = "add\\s[12]\\d{3}[-](([0][1-9])|([1][0-2]))[-](([0][1-9])|([12][0-9])|([3][01]))\\s\\d+(\\.[\\d]{1,2})?\\s[A-Z]{3}\\s.{3,100}";


    @Test
    public void testExecute(){

        final String RIGHT_COMMAND =             "add 2017-04-25 12 USD Jogurt";
        final String WRONG_COMMAND =             "addw 2017-04-25 12 USD Jogurt";
        final String WRONG_COMMAND_NOT_FULL =    "add 2017-04-25 12 USD";
        final String WRONG_COMMAND_NOT_FULL_1 =  "add 2017-04-25 12 Jogurt";
        final String WRONG_COMMAND_NOT_FULL_2 =  "add 2017-04-25 USD Jogurt";
        final String WRONG_COMMAND_NOT_FULL_3 =  "add 2017-04- 12 USD Jogurt";
        final String WRONG_COMMAND_NOT_FULL_4 =  "add 12 USD Jogurt";
        final String WRONG_COMMAND_DATE =        "add 2017-14-25 12 USD Jogurt";
        final String WRONG_COMMAND_DATE_1 =      "add 2017-12-40 12 USD Jogurt";

        assertTrue("RIGHT_COMMAND", checkCommand(RIGHT_COMMAND, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND",checkCommand(WRONG_COMMAND, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND_NOT_FULL",checkCommand(WRONG_COMMAND_NOT_FULL, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND_NOT_FULL_1",checkCommand(WRONG_COMMAND_NOT_FULL_1, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND_NOT_FULL_2",checkCommand(WRONG_COMMAND_NOT_FULL_2, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND_NOT_FULL_3",checkCommand(WRONG_COMMAND_NOT_FULL_3, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND_NOT_FULL_4",checkCommand(WRONG_COMMAND_NOT_FULL_4, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND_DATE",checkCommand(WRONG_COMMAND_DATE, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND_DATE_1",checkCommand(WRONG_COMMAND_DATE_1, COMMAND_PARSER));
    }


    @Test
    public void execute() {

        final String RIGHT_CURRENCY   = "USD";
        final String WRONG_CURRENCY_1 = "USDD";
        final String WRONG_CURRENCY_2 = "UD";
        final String WRONG_CURRENCY_3 = "UDD";

        assertTrue("RIGHT_CURRENCY", isCurrencyTypeCorrect(RIGHT_CURRENCY));
        assertFalse("WRONG_CURRENCY_1", isCurrencyTypeCorrect(WRONG_CURRENCY_1));
        assertFalse("WRONG_CURRENCY_2", isCurrencyTypeCorrect(WRONG_CURRENCY_2));
        assertFalse("WRONG_CURRENCY_3", isCurrencyTypeCorrect(WRONG_CURRENCY_3));
    }

    @Override
    public void execute(String inCommand) {
//
    }

    @Override
    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }
}