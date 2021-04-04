package Fake;

import Display.Console.FakeInputConsole;
import Display.Files.TestOutput;
import Engine.InputController;
import Engine.OutputController;
import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import Source.ReadDelimitedFile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static Source.DataSourceConstants.*;

/*
This test fakes user input by reading from system input logs.
The class TestOutput implements the OutputController interface,
this class utilises core program logic and does not display anything.

The assert checks system output logs match the values recorded in the TestOutput class
 */

class FakeTest {

    private Program getProgramWithTestData(){
        ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();
        String[] recordedInput = readDelimitedFile.getFileData(SYSTEM_LOGS,SYSTEM_INPUT).get(0);

        double frequency = Double.parseDouble(recordedInput[0]);
        Frequency units = Frequency.valueOf(recordedInput[1]);

        String[] instructions = Arrays.copyOfRange(recordedInput,2,recordedInput.length);
        ArrayList<InstructionType> fileInputInstructions = new ArrayList<>();
        for (int i = 0; i < instructions.length; i=i+3) {
            fileInputInstructions.add(new InstructionType(
                    instructions[i],
                    Integer.valueOf(instructions[i+1]),
                    Integer.valueOf(instructions[i+2])));
        }

        return new Program(frequency,units,fileInputInstructions);
    }

    @Test
    void fakeTest2(){
        ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();
        List<String> recordedOutput = new ArrayList<>(
                                        Arrays.asList(
                                        readDelimitedFile.getFileData(SYSTEM_LOGS,SYSTEM_OUTPUT).get(0)));

        //Output = testOutput runs without displaying anything (system logs are still used)
        TestOutput output = new TestOutput();
        Program program = getProgramWithTestData();
        output.run(program);

        System.out.println("Recorded: " + recordedOutput);
        System.out.println("Test:     "+ output.getTestOutputs());
        assertEquals(recordedOutput,output.getTestOutputs());
    }
}
