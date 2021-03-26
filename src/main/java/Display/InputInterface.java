package Display;

import Entities.Program;

public interface InputInterface {

    Program getAllData();
    void setInputToFile(boolean inputToFile, boolean appendToFile);
    String getInputString(String message);
    int getInputInt();
}


