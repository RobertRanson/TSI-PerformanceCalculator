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

class InputLogsTest {

    ReadDelimitedFile readDelimitedFile = new ReadDelimitedFile();
    @Test
    void displayOutputMock() {

        //Setup
        OutputLogs outputLogs = new OutputLogs();

        ArrayList<InstructionType> instructionTypes = new ArrayList<>();
        instructionTypes.add(new InstructionType("FloatMock",100000,2));
        instructionTypes.add(new InstructionType("Control",20000,8));
        Program programMock = new Program(50.0F, Frequency.decahertz,instructionTypes);

        //Mock
        InputLogs mockInputLogs = spy(InputLogs.class);
        when(mockInputLogs.run()).thenReturn(programMock);
        outputLogs.run(mockInputLogs.run());

        //Assert
         assertEquals("FloatMock",readDelimitedFile.getFileData("SystemLogs/","systemOutputLog.csv").get(0)[2]);
         assertEquals("720.0",readDelimitedFile.getFileData("SystemLogs/","systemOutputLog.csv").get(0)[12]);
    }
}