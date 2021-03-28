package Utilities;

import Source.WriteToFile;
import static Source.DataSourceConstants.*;

public class ErrorLogging {

    private static ErrorLogging singleInstance = new ErrorLogging();

    private WriteToFile writeErrorLog = new WriteToFile(ERRORLOG_FOLDER,ERROR_LOG,true);

    private ErrorLogging(){

    }
    public static ErrorLogging getInstance(){
        return singleInstance;
    }

    public void writeToLog(String method, String message) {
        System.out.println("Method: " + method + " Message: " + message);
        this.writeErrorLog.write(method + "," + message);
    }
}
