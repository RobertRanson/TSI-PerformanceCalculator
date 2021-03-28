package Display.Console;

import Engine.Logic;
import Engine.OutputController;
import Entities.InstructionType;
import Entities.Program;

import java.util.ArrayList;

public class OutputConsole extends OutputController {

    private String delimiter = ",";

    public void DisplayOutput(Program program) {

        logEvent(program.getClockFrequency()+delimiter);
        System.out.println("Frequency: "+ program.getClockFrequency());

        //todo dont log floats in exp notation

        logEvent(program.getTotalInstructionCount()+delimiter);
        System.out.println("Program Instruction Count: " + program.getTotalInstructionCount());


        System.out.println("------------------------------------");

        for (InstructionType inst : program.getInstructions()) {

            for (String info: inst.getinstructioninfo()) { System.out.println(info); }
            logEvent(inst.getType() + delimiter);
            logEvent(inst.getInstructionCount() + delimiter);
            logEvent(inst.getCyclesPerInstruction() + delimiter);
            logEvent(inst.getExecutionTime()+"");
        }
        logEvent(Logic.calculateAverageCPI(program)+"");
        System.out.println("CPI: " + Logic.calculateAverageCPI(program));
        logEvent(Logic.calculateMipsRate(program)+"");
        System.out.println("MIPS Rate: " + Logic.calculateMipsRate(program));
        logEvent(Logic.calculateExecutionTime(program)+"");
        System.out.println("Execution Time: " + Logic.calculateExecutionTime(program));

        logEvent("\n");
    }


}
