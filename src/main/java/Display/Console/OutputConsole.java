package Display.Console;

import Engine.OutputController;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingServiceInterface;
import static Source.DataSourceConstants.*;

public class OutputConsole implements LoggingServiceInterface, OutputController {

    public void run(Program program) {

        this.setLogFile(SYSTEM_LOGS,SYSTEM_OUTPUT,false);

        //CPU Information
        System.out.println("Frequency: "+ this.getClockFrequency(program,this));
        //todo dont log floats in exp notation
        System.out.println("Program Instruction Count: " + this.getTotalInstructionCount(program,this));

        //Instruction Information
        System.out.println("------------------------------------");

        for (InstructionType inst : program.getInstructions()) {
            System.out.println("Type: " + this.getInstructionType(inst,this));
            System.out.println("Count: " + this.getInstructionCount(inst,this));
            System.out.println("CPI: " + this.getInstructionCpi(inst,this));
            System.out.println("Exec Time: " + this.getInstructionExec(program,inst,this));
        }

        //Results

        System.out.println("CPI: " + this.getAverageCpi(program,this));
        System.out.println("MIPS Rate: " + this.getMipsRate(program,this));
        System.out.println("Execution Time: " + this.getExecTime(program,this));
    }
}
