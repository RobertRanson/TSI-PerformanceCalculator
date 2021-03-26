package Display;

import AppDataSource.WriteToFile;
import Entities.Program;

public abstract class OutputController {

    public boolean loggingOn;
    private AppDataSource.WriteToFile outputFile;

    public void loggingSettings(boolean loggingOn, boolean loggingOverwrite) {
        if (loggingOn) {
            outputFile = new WriteToFile("UserActions/", "userOutputLog.csv", loggingOverwrite);
            this.loggingOn = loggingOn;
        }
    }

    public void logEvent(String message) {
        if (loggingOn) {
            outputFile.write(message);
        }

    }

    abstract void DisplayOutput(Program program);
}
