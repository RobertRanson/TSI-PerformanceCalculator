package Display;

import static AppDataSource.DataSourceConstants.*;
import AppDataSource.WriteToFile;
import Entities.InstructionType;
import Entities.Program;

public class OutputConsole implements Display.OutputInterface {

    private boolean outputToFile = false;

    private AppDataSource.WriteToFile outputFile;

    public void setOutputToFile(boolean outputToFile, boolean appendToFile) {
        if (outputToFile) {
            outputFile = new AppDataSource.WriteToFile(USERACTION_FOLDER, OUTPUT_LOG, appendToFile);
            this.outputToFile = outputToFile;
        }
    }

    public void output(String message){
        System.out.println(message);
        if (outputToFile) {
            outputFile.write(message);
        }
    }

    @Override
    public void DisplayOutput(Program program, boolean outputToFile, boolean appendToFile) {

        setOutputToFile(outputToFile,appendToFile);


        output("Frequency: " + program.getClockFrequency() + ",");
        //todo dont log floats in exp notation
        output("Instruction Count: " + program.getTotalInstructionCount() + ",");
        for (InstructionType inst :
                program.getInstructions()) {
            output(inst.toString()+ ",");
        }
        output("\n");
    }


}
