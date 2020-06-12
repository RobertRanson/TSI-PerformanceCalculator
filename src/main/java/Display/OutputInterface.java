package Display;

public interface OutputInterface {
    public void setOutputToFile(boolean outputToFile, boolean appendToFile);
    public void output(String message);
}
