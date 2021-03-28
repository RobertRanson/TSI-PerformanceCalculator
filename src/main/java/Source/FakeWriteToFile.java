package Source;

public class FakeWriteToFile implements WriteToFileInterface{

    @Override
    public void setFileWriter(String directory, String fileName, boolean appendToFile) {

    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void closeFileWriter() {

    }
}
