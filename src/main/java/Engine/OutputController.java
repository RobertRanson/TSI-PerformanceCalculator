package Engine;

import Entities.InstructionType;
import Source.LoggingServiceInterface;
import Entities.Program;

public interface OutputController {
    String delimiter = ",";


   default double getClockFrequency(Program program, LoggingServiceInterface self){
       double clockFrequency = program.getClockFrequency();
       self.systemLog(clockFrequency+delimiter);
        return clockFrequency;
   }

   default int getTotalInstructionCount(Program program, LoggingServiceInterface self){
       int instructionCount = program.getTotalInstructionCount();
       self.systemLog(instructionCount+delimiter);
       return instructionCount;
   }

   default String getInstructionType(InstructionType instructionType, LoggingServiceInterface self){
       String type = instructionType.getType();
       self.systemLog(type+delimiter);
       return type;
   }

   default int getInstructionCount(InstructionType instructionType, LoggingServiceInterface self){
       int count = instructionType.getInstructionCount();
       self.systemLog(count+delimiter);
       return count;
   }

   default double getInstructionCpi(InstructionType instructionType, LoggingServiceInterface self){
       double cpi = instructionType.getCyclesPerInstruction();
       self.systemLog(cpi+delimiter);
       return cpi;
   }

   default double getInstructionExec(Program program, InstructionType instructionType, LoggingServiceInterface self){
       double time = instructionType.getExecutionTime();
       self.systemLog(time+delimiter);
       return time;
   }

   default double getAverageCpi(Program program, LoggingServiceInterface self){
       double cpi = Logic.calculateAverageCPI(program);
       self.systemLog(cpi+delimiter);
       return cpi;
   }

    default double getMipsRate(Program program, LoggingServiceInterface self){
        double mips = Logic.calculateMipsRate(program);
        self.systemLog(mips+delimiter);
        return mips;
    }
    default double getExecTime(Program program, LoggingServiceInterface self){
        double time = Logic.calculateExecutionTime(program);
        self.systemLog(time+delimiter);
        return time;
    }

    void run(Program program);
}
