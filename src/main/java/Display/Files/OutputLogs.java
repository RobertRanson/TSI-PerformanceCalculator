package Display.Files;

import Engine.Logic;
import Engine.OutputController;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingService;

public class OutputLogs extends OutputController {

    private LoggingService loggingService;
    private String deliminator = ",";


    private void setLoggingService(LoggingService ls) { loggingService = ls; }

    @Override
    public void DisplayOutput(Program program) {
        setLoggingService(LoggingService.getInstance());
        loggingService.setLogFile("UserActions/", "FileOutput.txt",false);

        float clockFrequency = program.getClockFrequency();
        loggingService.log("Frequency: "+ clockFrequency);
        logEvent(clockFrequency + deliminator);

        int instructionCount = program.getTotalInstructionCount();
        loggingService.log("Instruction Count: " + instructionCount);
        logEvent(instructionCount+deliminator);

        for (InstructionType inst : program.getInstructions()) {

            for (String info: inst.getinstructioninfo()) {

                String[] message = info.split(": ");
                loggingService.log(info);
                logEvent(message[1]+deliminator);
            }
        }

        float averageCpi = Logic.calculateAverageCPI(program);
        loggingService.log("CPI: " + averageCpi);
        logEvent(averageCpi+deliminator);

        float mipsRate = Logic.calculateMipsRate(program);
        loggingService.log("MIPS Rate: " + mipsRate);
        logEvent(mipsRate+deliminator);

        float executionTime = Logic.calculateExecutionTime(program);
        loggingService.log("Execution Time: " + executionTime);
        logEvent(executionTime+deliminator);

        logEvent("\n");
    }
}
