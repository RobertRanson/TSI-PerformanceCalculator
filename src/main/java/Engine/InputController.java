package Engine;

import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingServiceInterface;
import Utilities.ErrorLogging;

import java.util.ArrayList;

public interface InputController {

    String delimiter = ",";
    Program program = new Program();

    default void setClockFrequency(String inputFreq, String inputUnits, LoggingServiceInterface self) {
        double clockFrequency = stringTodouble(inputFreq);
        Frequency units = Frequency.valueOf(inputUnits);
        self.systemLog(clockFrequency + delimiter);
        self.systemLog(units + delimiter);
        program.setClockFrequency(clockFrequency, units);
        program.setInstructions(new ArrayList<>());
    }

    default void addInstruction(String type, String count, String cpi, LoggingServiceInterface self) {
        int instCount = Integer.valueOf(count);
        int instCpi = Integer.valueOf(cpi);
        self.systemLog(type + delimiter);
        self.systemLog(instCount + delimiter);
        self.systemLog(instCpi + delimiter);

        InstructionType newInst = new InstructionType(type, instCount, instCpi);
        newInst.calculateExecutionTime(Logic.frequencyToPeriod(program.getClockFrequency(), Frequency.hertz));
        program.addInstruction(newInst);
    }

    default double stringTodouble(String string) {
        double result = 0;
        try {
            result = Double.valueOf(string);
        } catch (Exception e) {
            ErrorLogging.getInstance().writeToLog("stringTodouble", e.getMessage());
        } finally {
            return result;
        }

    }
    Program run();
}
