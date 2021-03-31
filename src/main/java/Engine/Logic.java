package Engine;


import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Logic {

    private static DecimalFormat df = new DecimalFormat("##.######");

    public static double frequencyToHertz(Frequency currentUnits, double currentValue) {
        double returnValue = currentValue;
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
        return (returnValue);
    }

    public static double frequencyToPeriod(double currentFrequency, Frequency currentUnits) {
        return (1 / frequencyToHertz(currentUnits, currentFrequency));
    }

    public static double calculateAverageCPI(Program program) {
        ArrayList<InstructionType> allInstructions = program.getInstructions();
        double sum = 0;
        for (InstructionType instruction : allInstructions) {
            sum += instruction.getCyclesPerInstruction() * instruction.getInstructionCount();
        }
        return (sum / program.getTotalInstructionCount());
    }

    public static double calculateExecutionTime(Program program) {
        ArrayList<InstructionType> allInstructions = program.getInstructions();
        double runtime = 0;
        double clockTime = frequencyToPeriod(program.getClockFrequency(), Frequency.hertz);
        for (InstructionType instruction : allInstructions) {
            System.out.println(instruction.toString());
            instruction.calculateExecutionTime(clockTime);
            runtime += instruction.getExecutionTime();
        }
        return (runtime);
    }

    public static double calculateMipsRate(Program program) {
        System.out.println(program.getTotalInstructionCount()+" / "+ Logic.calculateExecutionTime(program));
        return ((program.getTotalInstructionCount() / (Logic.calculateExecutionTime(program) * 1000000)));
    }

    public static float trimFloat(float input){
        System.out.println("trimFloat: " + input);
        return Float.valueOf(df.format(input));
    }
    public static String floatToString(float fl){
        return df.format(fl);
    }
    public static float stringToFloat(String st){
        return trimFloat(Float.valueOf(st));
    }
}
