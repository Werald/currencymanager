import com.poodel.commands.FatherOfCommands;
import com.poodel.commands.commands_implementation.ClearCommand;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit-test для щаблона команды list.
 * @see ClearCommand
 */
public class ListCommandTest extends FatherOfCommands {
    private static final String COMMAND_PARSER = "list";

    @Test
    public void testExecute() {
        final String RIGHT_COMMAND = "list";
        final String WRONG_COMMAND = "lost";
        final String WRONG_COMMAND1 = "List";
        final String WRONG_COMMAND2 = "list ";

        assertTrue("RIGHT_COMMAND", checkCommand(RIGHT_COMMAND, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND",checkCommand(WRONG_COMMAND, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND1",checkCommand(WRONG_COMMAND1, COMMAND_PARSER));
        assertFalse("WRONG_COMMAND2",checkCommand(WRONG_COMMAND2, COMMAND_PARSER));
    }

    @Override
    public void execute(String inCommand) {

    }

    @Override
    public String[] getDataArrFromUI(String inCommand) {
        return new String[0];
    }
}