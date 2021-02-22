package Display;

import AppDataSource.DataSourceConstants;
import AppDataSource.DataSourceConstants.*;

import java.util.Scanner;

import static AppDataSource.DataSourceConstants.INPUT_LOG;
import static AppDataSource.DataSourceConstants.USERACTION_FOLDER;

public class InputConsole extends InputInt {
    private Scanner userInput = new Scanner(System.in);
    private boolean inputToFile = false;

    private AppDataSource.WriteToFile inputFile;

    public void setInputToFile(boolean inputToFile, boolean appendToFile){
        if (inputToFile){
            inputFile = new AppDataSource.WriteToFile(USERACTION_FOLDER, INPUT_LOG, appendToFile);
        }
        this.inputToFile = inputToFile;
    }

    public String getInputString(){
        String result = userInput.nextLine();
        if (inputToFile) {
            inputFile.write(result);
        }
        return result;
    }
}
