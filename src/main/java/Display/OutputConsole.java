package Display;

import static AppDataSource.DataSourceConstants.*;
import AppDataSource.WriteToFile;

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

}
