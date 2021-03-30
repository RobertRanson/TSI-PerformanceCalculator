package Engine;

import Entities.InstructionType;
import Source.LoggingServiceInterface;
import Entities.Program;

public interface OutputController {
    String delimiter = ",";


   default float getClockFrequency(Program program, LoggingServiceInterface self){
       float clockFrequency = program.getClockFrequency();
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

   default float getInstructionCpi(InstructionType instructionType, LoggingServiceInterface self){
       float cpi = instructionType.getCyclesPerInstruction();
       self.systemLog(cpi+delimiter);
       return cpi;
   }

   default float getInstructionExec(InstructionType instructionType, LoggingServiceInterface self){
       float time = instructionType.getExecutionTime();
       self.systemLog(time+delimiter);
       return time;
   }

   default float getAverageCpi(Program program, LoggingServiceInterface self){
       float cpi = Logic.calculateAverageCPI(program);
       self.systemLog(cpi+delimiter);
       return cpi;
   }

    default float getMipsRate(Program program, LoggingServiceInterface self){
        float mips = Logic.calculateMipsRate(program);
        self.systemLog(mips+delimiter);
        return mips;
    }
    default float getExecTime(Program program, LoggingServiceInterface self){
        float time = Logic.calculateExecutionTime(program);
        self.systemLog(time+delimiter);
        return time;
    }

    void run(Program program);
}
