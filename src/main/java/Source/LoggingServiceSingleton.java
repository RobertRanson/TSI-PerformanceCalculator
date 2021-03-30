package Source;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Source.DataSourceConstants.RESOURCE;

public class LoggingServiceSingleton implements LoggingServiceInterface {

    private FileWriter fileWriter;
    private String filePathPrefix = RESOURCE;
    private String logDelimitor = ",";

    private static LoggingServiceSingleton uniqueInstance;

    public static LoggingServiceSingleton getInstance() {
        if (uniqueInstance==null) {
            uniqueInstance = new LoggingServiceSingleton();
        }
        return uniqueInstance;
    }


    private LoggingServiceSingleton(){ }

    public void setLogDelimitor(String logDelimitor){
        this.logDelimitor = logDelimitor;
    }

    public List<String> getLogData(String directory, String fileName){

        List<String> logData = new ArrayList<String>();

        try {
            File propertyFile = new File(filePathPrefix+ directory + fileName);
            Scanner propertyReader = new Scanner(propertyFile);
            System.out.println(propertyFile.getPath());
            while (propertyReader.hasNextLine()) {

                String fileRow = propertyReader.nextLine();
                logData.add(fileRow);
            }
            propertyReader.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return logData;
    }

    public void setLogFile(String directory, String fileName, boolean appendToFile){

        try {
            fileWriter = new FileWriter(filePathPrefix + directory+ fileName, appendToFile);

        } catch (IOException ioExp) {
            System.out.println("An error occurred.");
            ioExp.printStackTrace();
        }
    }

    public void log(String message){

        try {
            fileWriter.write(message);
            fileWriter.flush();

        } catch (IOException ioExp) {
            System.out.println("An error occurred.");
            ioExp.printStackTrace();
        }
    }
}
