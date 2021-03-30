package Engine;


import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import org.sqlite.date.DateFormatUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Logic {

    private static DecimalFormat df = new DecimalFormat("##.######");

    public static float frequencyToHertz(Frequency currentUnits, float currentValue) {
        float returnValue = currentValue;
        switch (currentUnits) {
            case decahertz:
                returnValue *= 10;
                break;
            case kilohertz:
                returnValue *= 1000;
                break;

            case megahertz:
                returnValue *= 1000000;
                break;
            case gigahertz:
                returnValue *= 1000000000;
                break;
            default:
                break;
        }
        return trimFloat(returnValue);
    }

    public static float frequencyToPeriod(float currentFrequency, Frequency currentUnits) {
        return trimFloat(1 / frequencyToHertz(currentUnits, currentFrequency));
    }

    public static float calculateAverageCPI(Program program) {
        ArrayList<InstructionType> allInstructions = program.getInstructions();
        float sum = 0;
        for (InstructionType instruction : allInstructions) {
            sum += instruction.getCyclesPerInstruction() * instruction.getInstructionCount();
        }
        return trimFloat(sum / program.getTotalInstructionCount());
    }

    public static float calculateExecutionTime(Program program) {
        ArrayList<InstructionType> allInstructions = program.getInstructions();
        float runtime = 0;
        float clockTime = frequencyToPeriod(program.getClockFrequency(), Frequency.hertz);
        for (InstructionType instruction : allInstructions) {
            instruction.calculateExecutionTime(clockTime);
            runtime += instruction.getExecutionTime();
        }
        return trimFloat(runtime);
    }

    public static float calculateMipsRate(Program program) {
        return trimFloat((program.getTotalInstructionCount() / (Logic.calculateExecutionTime(program) * 1000000)));
    }

    public static float trimFloat(float input){
        return Float.valueOf(df.format(input));
    }
    public static String floatToString(float fl){
        return df.format(fl);
    }
    public static float stringToFloat(String st){
        return trimFloat(Float.valueOf(st));
    }
}
