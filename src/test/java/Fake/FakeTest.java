package Fake;

import Display.Console.FakeInputConsole;
import Display.Files.InputStub;
import Display.Files.TestOutput;
import Engine.InputController;
import Engine.OutputController;
import Entities.Program;
import Source.ReadDelimitedFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static Source.DataSourceConstants.*;

class FakeTest {



    @Test
    void fakeTest(){
        //Input = fake class that returns values from array
        InputController input = new FakeInputConsole();
        ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();

        //Output = testOutput which runs without displaying anything (system logs are still used)
        OutputController output = new TestOutput();


        //Run
        //program contains return from fake input
        Program program = input.run();
        output.run(program);

        //Assert fake input data has been transformed and logged in system_output.csv
        int actual = Integer.valueOf(readDelimitedFile.getFileData(SYSTEM_LOGS,SYSTEM_OUTPUT).get(0)[1]);
        assertEquals(program.getTotalInstructionCount(), actual);
    }
}
