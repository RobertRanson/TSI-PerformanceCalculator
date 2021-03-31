package Display.Files;

import Engine.InputController;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingServiceInterface;

import java.util.ArrayList;

import static Source.DataSourceConstants.SYSTEM_INPUT;
import static Source.DataSourceConstants.SYSTEM_LOGS;

public class InputLogs implements InputController, LoggingServiceInterface {

    @Override
    public Program run() {

        ArrayList<String> data = (ArrayList<String>) loggingService.getLogData("UserActions/", "FileInput.txt");
        this.setLogFile(SYSTEM_LOGS, SYSTEM_INPUT, false);

        //CPU Information

        this.setClockFrequency(
                data.get(0),
                data.get(1),
                this
        );

        //Instruction Information

        //int numberOfInstructions = Integer.valueOf(data.get(2));
        ArrayList<InstructionType> fileInputInstructions = new ArrayList<>();

        for (int i = 3; i < data.size(); i = i + 3) {
            this.addInstruction(
                    data.get(i),
                    data.get(i + 1),
                    data.get(i + 2),
                    this
            );
        }
        return (this.program);
    }
}
