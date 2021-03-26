package Display;

import Entities.Program;

public interface OutputInterface {
    public void setOutputToFile(boolean outputToFile, boolean appendToFile);
    public void output(String message);
    public void DisplayOutput(Program program,boolean outputToFile, boolean appendToFile);
}
