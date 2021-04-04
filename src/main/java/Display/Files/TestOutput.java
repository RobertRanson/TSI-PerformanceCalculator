package Display.Files;

import Engine.OutputController;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingServiceInterface;

import java.util.ArrayList;
import java.util.List;

import static Source.DataSourceConstants.SYSTEM_LOGS;
import static Source.DataSourceConstants.SYSTEM_OUTPUT;

/*
This class is created for testing only.
It implements the OutputController interface and its
required method run(). It is designed to work the same
as other classes that implement OutputController by utilising
the logging service, but without displaying to user.
 */

public class TestOutput implements OutputController, LoggingServiceInterface {
    private List<String> testOutputs = new ArrayList<>();

    public List<String> getTestOutputs() {
        return testOutputs;
    }
    public void clearTestOutputs(){
        testOutputs.clear();
    }
    public void addTestOutputs(String item){
        testOutputs.add(item);
    }

    @Override
    public void run(Program program) {

        loggingService.setLogFile(SYSTEM_LOGS, SYSTEM_OUTPUT, false);
        addTestOutputs(String.valueOf(this.getClockFrequency(program, this)));
        addTestOutputs(String.valueOf(this.getTotalInstructionCount(program, this)));
        for (InstructionType inst : program.getInstructions()) {
            addTestOutputs(this.getInstructionType(inst, this));
            addTestOutputs(String.valueOf(this.getInstructionCount(inst, this)));
            addTestOutputs(String.valueOf(this.getInstructionCpi(inst, this)));
            addTestOutputs(String.valueOf(this.getInstructionExec(inst, this)));
        }
        addTestOutputs(String.valueOf(this.getAverageCpi(program, this)));
        addTestOutputs(String.valueOf(this.getMipsRate(program, this)));
        addTestOutputs(String.valueOf(this.getExecTime(program, this)));
    }
}
