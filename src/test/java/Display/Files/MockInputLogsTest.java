package Display.Files;

import Engine.InputController;
import Engine.OutputController;
import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import Source.ReadDelimitedFile;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

/*
This test mocks the class InputLogs.
It creates a Program type which is returned when
the abstract InputController.run()
The class OutputLogs implements the OutputController and
utilises core program logic.
Then assert system logs match the mock inputs and that
system logs contain correct output
 */

class MockInputLogsTest {

    @Test
    void displayOutputMock() {

        //Setup
        ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();
        OutputController outputLogs = new OutputLogs();

        ArrayList<InstructionType> instructionTypes = new ArrayList<>();
        instructionTypes.add(new InstructionType("FloatMock",100000,2));
        instructionTypes.add(new InstructionType("Control",20000,8));
        Program mockData = new Program(50.0F, Frequency.decahertz,instructionTypes);

        //Mock
        //Return mockData when InputLogs.run()
        InputController mockInputLogs = spy(InputLogs.class);
        when(mockInputLogs.run()).thenReturn(mockData);

        //Use mockData in OutputLogs
        outputLogs.run(mockInputLogs.run());

        //Example of over testing v v v
        //Assert logs contain 'FloatMock' from mockData & data from outputLogs.run()
        assertAll("Should return 1st instruction type and total exec time",
                () -> assertEquals("FloatMock",readDelimitedFile.getFileData("SystemLogs/","systemOutputLog.csv").get(0)[2]),
                () -> assertEquals("720.0",readDelimitedFile.getFileData("SystemLogs/","systemOutputLog.csv").get(0)[12]),
                () -> verify(mockInputLogs,times(2)).run());
    }
}