package Source;

public interface LoggingServiceInterface {

    LoggingServiceSingleton loggingService = LoggingServiceSingleton.getInstance();

    default void setLogFile(String directory, String fileName, boolean loggingAppend) {
        loggingService.setLogFile(directory, fileName, loggingAppend);
    }

    default void systemLog(String message) {
        loggingService.log(message);
    }
}
