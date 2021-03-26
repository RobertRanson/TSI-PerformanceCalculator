package Engine;


import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import java.util.ArrayList;

public class Logic {

    private static Entities.Frequency Frequency;

    public static float frequencyToHertz(Frequency currentUnits, float currentValue){
        float returnValue = currentValue;
        switch (currentUnits){
            case kilohertz:
                returnValue *= 1000;
                break;
            case decahertz:
                returnValue *= 10;
                break;
            case megahertz:
                returnValue *= 1000000;
                break;
            case gighertz:
                returnValue *= 1000000000;
                break;
            default:
                break;
        }
        return returnValue;
    }

    public static float frequencyToPeriod(float currentFrequency, Frequency currentUnits){
        return (1/frequencyToHertz(currentUnits,currentFrequency));
    }

    public static float calculateAverageCPI(Program program){
        ArrayList<InstructionType> allInstructions = program.getInstructions();
        float sum = 0;
        for (InstructionType instruction: allInstructions) {
            sum += instruction.getCyclesPerInstruction() * instruction.getInstructionCount();
        }
        return (sum/program.getTotalInstructionCount());
    }

    public static float calculateExecutionTime(Program program){
        ArrayList<InstructionType> allInstructions = program.getInstructions();
        float runtime = 0;
        float clockTime = frequencyToPeriod(program.getClockFrequency(), Frequency.hertz);
        for (InstructionType instruction: allInstructions) {
            instruction.calculateExecutionTime(clockTime);
            runtime += instruction.getExecutionTime();
        }
        return runtime;
    }

    public static float calculateMipsRate(Program program){
        return (program.getTotalInstructionCount()/(Logic.calculateExecutionTime(program) * 1000000));
    }
}
