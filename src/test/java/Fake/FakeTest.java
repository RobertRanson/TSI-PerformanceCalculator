package Fake;

import Display.Files.InputStub;
import Display.Files.TestOutput;
import Engine.InputController;
import Engine.OutputController;
import Entities.Program;
import Source.ReadDelimitedFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static Source.DataSourceConstants.*;

class FakeTest {

    ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();

    @Test
    void fakeTest(){
        InputController input = new InputStub();
        OutputController output = new TestOutput();
        Program program = input.run();
        output.run(program);

        //Assert

        int actual = Integer.valueOf(readDelimitedFile.getFileData(SYSTEM_LOGS,SYSTEM_OUTPUT).get(0)[1]);
        assertEquals(program.getTotalInstructionCount(), actual);
    }
}
