package Source;
import static Source.DataSourceConstants.*;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile implements WriteToFileInterface {

    public WriteToFile(String directory, String fileName, boolean appendToFile){
        this.setFileWriter(directory,fileName,appendToFile);
    }

    private FileWriter fileWriter;
    private String filePathPrefix = RESOURCE;



    @Override
    public void setFileWriter(String directory, String fileName, boolean appendToFile){
        System.out.println(filePathPrefix + directory+ fileName);
        try {
            fileWriter = new FileWriter(filePathPrefix + directory+ fileName, appendToFile);
        } catch (IOException ioExp) {
            System.out.println("An error occurred.");
            ioExp.printStackTrace();
        }
    }

    @Override
    public void write(String message){
        try {
            fileWriter.write(message);
            fileWriter.flush();
        } catch (IOException ioExp) {
            System.out.println("An error occurred.");
            ioExp.printStackTrace();
        }
    }

    @Override
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

