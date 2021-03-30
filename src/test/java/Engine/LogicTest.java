package Engine;

import Display.Files.InputStub;
import Entities.Program;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    InputStub input = new InputStub();


    @Test
    void calculateAverageCPI() {
        Program program = input.DisplayOutput();
        float cpi = Logic.calculateAverageCPI(program);
        assertEquals(1.375,cpi);
    }
}