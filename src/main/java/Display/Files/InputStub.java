package Display.Files;

import Engine.InputController;
import Entities.Frequency;
import Entities.InstructionType;
import Source.DataSourceConstants;
import Entities.Program;
import Source.LoggingServiceInterface;

import java.util.ArrayList;

import static Source.DataSourceConstants.SYSTEM_INPUT;
import static Source.DataSourceConstants.SYSTEM_LOGS;

public class InputStub implements InputController, LoggingServiceInterface {
    @Override
    public Program run() {

        this.setLogFile(SYSTEM_LOGS,SYSTEM_INPUT,false);

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
