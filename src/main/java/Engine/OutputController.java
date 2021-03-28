package Engine;

import Source.WriteToFile;
import Entities.Program;

public abstract class OutputController {

    public boolean loggingOn;
    public Source.WriteToFileInterface outputFile;

    public void loggingSettings(boolean loggingOn, boolean loggingAppend) {
        if (loggingOn) {
            outputFile = new WriteToFile("SystemLogs/", "systemOutputLog.csv", loggingAppend);
            this.loggingOn = loggingOn;
        }
    }

    public void logEvent(String message) {
        if (loggingOn) {
            outputFile.write(message);
        }

    }

    public abstract void DisplayOutput(Program program);
}
