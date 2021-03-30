package Display.Files;

import Engine.Logic;
import Engine.OutputController;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingService;
import Source.LoggingServiceInterface;

import static Source.DataSourceConstants.SYSTEM_LOGS;
import static Source.DataSourceConstants.SYSTEM_OUTPUT;

public class OutputLogs implements OutputController, LoggingServiceInterface {

    private String deliminator = ",";

    @Override
    public void run(Program program) {

        //CPU Information

        loggingService.setLogFile(SYSTEM_LOGS,SYSTEM_OUTPUT,false);
        float clockFrequency = this.getClockFrequency(program,this);
        float totalCount = this.getTotalInstructionCount(program,this);

        loggingService.setLogFile("UserActions/", "FileOutput.txt",false);
        this.systemLog("Clock Frequency: "+clockFrequency+"\n");
        this.systemLog("Total Instruction Count: "+totalCount+"\n");

        //Instruction Information

        for (InstructionType inst : program.getInstructions()) {

            loggingService.setLogFile(SYSTEM_LOGS,SYSTEM_OUTPUT,true);
            String type = this.getInstructionType(inst,this);
            int count =this.getInstructionCount(inst,this);
            float cpi = this.getInstructionCpi(inst,this);
            float time = this.getInstructionExec(inst,this);

            loggingService.setLogFile("UserActions/", "FileOutput.txt",true);
            this.systemLog("Type: "+type+" ");
            this.systemLog("Count: "+count+" ");
            this.systemLog("Cpi: "+cpi+" ");
            this.systemLog("Exec Time: "+time+"\n");
        }


        //Results
        loggingService.setLogFile(SYSTEM_LOGS,SYSTEM_OUTPUT,true);
        float averageCpi = this.getAverageCpi(program,this);
        float mipsRate = this.getMipsRate(program,this);
        float executionTime = this.getExecTime(program,this);

        loggingService.setLogFile("UserActions/", "FileOutput.txt",true);
        this.systemLog("CPI: " + averageCpi);
        this.systemLog("MIPS Rate: " + mipsRate);
        this.systemLog("Execution Time: " + executionTime);

    }
}
