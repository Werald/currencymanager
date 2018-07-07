import com.poodel.commands.FatherOfCommands;
import com.poodel.database_manager.TableClear;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit-test для щаблона команды clear.
 */
public class ClearCommandTest extends FatherOfCommands {
    private static final String COMMAND_PARSER = "clear\\s[12]\\d{3}[-](([0][1-9])|([1][0-2]))[-](([0][1-9])|([12][0-9])|([3][01]))";

    @Test
    public void testExecute() {
        final String RIGHT_COMMAND = "clear 2017-04-25";
        final String WRONG_COMMAND = "clear 2017-14-25";
        final String WRONG_COMMAND1 = "cleare 2017-14-25";
        final String WRONG_COMMAND2 = "clear 2017-1-25";
        final String WRONG_COMMAND3 = "clear 2017-14-250";
        final String WRONG_COMMAND4 = "clear";
        final String WRONG_COMMAND5 = "clear 2017-14-25 ";

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
