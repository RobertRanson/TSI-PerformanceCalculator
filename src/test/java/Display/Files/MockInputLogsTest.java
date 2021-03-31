package Display.Files;

import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import Source.ReadDelimitedFile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class MockInputLogsTest {

    @Test
    void displayOutputMock() {

        //Setup
        ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();
        OutputLogs outputLogs = new OutputLogs();

        ArrayList<InstructionType> instructionTypes = new ArrayList<>();
        instructionTypes.add(new InstructionType("FloatMock",100000,2));
        instructionTypes.add(new InstructionType("Control",20000,8));
        Program programMock = new Program(50.0F, Frequency.decahertz,instructionTypes);

        //Mock
        //Return programMock when InputLogs.run()
        InputLogs mockInputLogs = spy(InputLogs.class);
        when(mockInputLogs.run()).thenReturn(programMock);
        //Use programMock in real output
        outputLogs.run(mockInputLogs.run());

        //Assert
        //Assert logs contain 'FloatMock' from programMock
         assertEquals("FloatMock",readDelimitedFile.getFileData("SystemLogs/","systemOutputLog.csv").get(0)[2]);
         //Assert logs contain correct data from outputLogs.run()
         assertEquals("720.0",readDelimitedFile.getFileData("SystemLogs/","systemOutputLog.csv").get(0)[12]);

    }
}