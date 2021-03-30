package Source;

public interface LoggingServiceInterface {

    LoggingService loggingService = LoggingService.getInstance();

    default void setLogFile(String directory, String fileName, boolean loggingAppend) { loggingService.setLogFile(directory,fileName,loggingAppend); }
    default void systemLog(String message){
        loggingService.log(message);
    }
}
