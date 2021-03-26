package Display;

import Engine.Logic;
import Entities.InstructionType;
import Entities.Program;

public class OutputConsole extends OutputController {


    public void DisplayOutput(Program program) {

        logEvent(program.getClockFrequency()+",");
        System.out.println("Frequency: "+ program.getClockFrequency());

        //todo dont log floats in exp notation

        logEvent(program.getTotalInstructionCount()+",");
        System.out.println("Instruction Count: " + program.getTotalInstructionCount());

        for (InstructionType inst : program.getInstructions()) {
            System.out.println(inst.toString());
            logEvent(inst.getType() + ",");
            logEvent(inst.getInstructionCount() + ",");
            logEvent(inst.getCyclesPerInstruction() + ",");
            logEvent(inst.getExecutionTime()+"");
        }
        logEvent(Logic.calculateAverageCPI(program)+"");
        System.out.println("\nCPI: " + Logic.calculateAverageCPI(program));
        logEvent(Logic.calculateMipsRate(program)+"");
        System.out.println("\nMIPS Rate: " + Logic.calculateMipsRate(program));
        logEvent(Logic.calculateExecutionTime(program)+"");
        System.out.println("\nExecution Time: " + Logic.calculateExecutionTime(program));

        logEvent("\n");
    }


}
