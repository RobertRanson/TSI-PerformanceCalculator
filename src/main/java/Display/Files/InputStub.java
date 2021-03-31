package Display.Files;

import Engine.InputController;
import Entities.Program;
import Source.LoggingServiceInterface;

import static Source.DataSourceConstants.SYSTEM_INPUT;
import static Source.DataSourceConstants.SYSTEM_LOGS;

/*
Input stub implements the InputController interface
and implements the required method run()
and then returns data type Program.
 */

public class InputStub implements InputController, LoggingServiceInterface {
    @Override
    public Program run() {

        this.setLogFile(SYSTEM_LOGS, SYSTEM_INPUT, false);

        this.setClockFrequency(
                "40",
                "kilohertz",
                this
        );

        this.addInstruction(
                "Control",
                "10000",
                "1",
                this
        );

        this.addInstruction(
                "Float",
                "6000",
                "2",
                this
        );

        return (this.program);
    }
}
