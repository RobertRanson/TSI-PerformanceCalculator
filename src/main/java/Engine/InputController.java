package Engine;

import Source.WriteToFile;
import Entities.Program;

public abstract class InputController {

    public boolean loggingOn;
    private Source.WriteToFile outputFile;

    public void loggingSettings(boolean loggingOn, boolean loggingAppend) {
        if (loggingOn) {

            outputFile = new WriteToFile("SystemLogs/", "systemInputLog.csv", loggingAppend);
            this.loggingOn = loggingOn;
        }
    }

    public void logEvent(String message) {
        if (loggingOn) {
            outputFile.write(message);
        }

    }

    public abstract Program DisplayOutput();
}
