import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.commands_implementation.ClearCommand;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit-test для щаблона команды total.
 * @see ClearCommand
 */
public class TotalCommandTest extends FatherOfCommands {
    private static final String COMMAND_PARSER = "[t][o][t][a][l]\\s[A-Z]{3}";

    @Test
    public void testExecute() {
        final String RIGHT_COMMAND = "total EUR";
        final String WRONG_COMMAND = "total EIRR";
        final String WRONG_COMMAND1 = "total 2017-14-25";
        final String WRONG_COMMAND2 = "total";
        final String WRONG_COMMAND3 = "total ЫЫЫ";
        final String WRONG_COMMAND4 = "Total EUR";
        final String WRONG_COMMAND5 = " total EUR";

        assertTrue("RIGHT_COMMAND", checkCommand(RIGHT_COMMAND, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND",checkCommand(WRONG_COMMAND, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND1",checkCommand(WRONG_COMMAND1, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND2",checkCommand(WRONG_COMMAND2, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND3",checkCommand(WRONG_COMMAND3, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND4",checkCommand(WRONG_COMMAND4, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND5",checkCommand(WRONG_COMMAND5, COMMAND_PARSER));
    }

    @Override
    public void execute(String inCommand) {

    }

    @Override
    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }
}