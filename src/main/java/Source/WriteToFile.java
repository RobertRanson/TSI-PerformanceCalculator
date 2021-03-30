package Source;
import static Source.DataSourceConstants.*;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public WriteToFile(String directory, String fileName, boolean appendToFile){
        this.setFileWriter(directory,fileName,appendToFile);
    }

    private FileWriter fileWriter;
    private String filePathPrefix = RESOURCE;



    public void setFileWriter(String directory, String fileName, boolean appendToFile){
        System.out.println(filePathPrefix + directory+ fileName);
        try {
            fileWriter = new FileWriter(filePathPrefix + directory+ fileName, appendToFile);
        } catch (IOException ioExp) {
            System.out.println("An error occurred.");
            ioExp.printStackTrace();
        }
    }

    public void write(String message){
        try {
            fileWriter.write(message);
            fileWriter.flush();
        } catch (IOException ioExp) {
            System.out.println("An error occurred.");
            ioExp.printStackTrace();
        }
    }


    public void closeFileWriter(){
        try {
            fileWriter.close();
        } catch (IOException ioExp) {
            System.out.println("An error occurred.");
            ioExp.printStackTrace();
        }
        this.fileWriter = null;
    }
}

