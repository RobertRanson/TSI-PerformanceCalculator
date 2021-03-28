package Source;

public interface WriteToFileInterface {
    void setFileWriter(String directory, String fileName, boolean appendToFile);

    void write(String message);

    void closeFileWriter();
}
