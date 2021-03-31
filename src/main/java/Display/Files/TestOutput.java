package Display.Files;

import Engine.OutputController;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingServiceInterface;

import static Source.DataSourceConstants.SYSTEM_LOGS;
import static Source.DataSourceConstants.SYSTEM_OUTPUT;

public class TestOutput implements OutputController, LoggingServiceInterface {

    @Override
    public void run(Program program) {

        loggingService.setLogFile(SYSTEM_LOGS, SYSTEM_OUTPUT, false);
        this.getClockFrequency(program, this);
        this.getTotalInstructionCount(program, this);
        for (InstructionType inst : program.getInstructions()) {
            this.getInstructionType(inst, this);
            this.getInstructionCount(inst, this);
            this.getInstructionCpi(inst, this);
            this.getInstructionExec(inst, this);
        }
        this.getAverageCpi(program, this);
        this.getMipsRate(program, this);
        this.getExecTime(program, this);
    }
}
