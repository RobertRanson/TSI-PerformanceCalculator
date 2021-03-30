package Engine;

import Display.Files.InputStub;
import Entities.Frequency;
import Entities.Program;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    //Stub Test using adapter
    InputController input = new InputStub();

    @Test
    void calculateAverageCPI() {
        Program program = input.run();
        float cpi = Logic.calculateAverageCPI(program);
        assertEquals(1.375,cpi);
    }


    @Test
    void frequencyToHertz() {
        Frequency frequency1 = Frequency.kilohertz;
        Frequency frequency2 = Frequency.hertz;

        float magnitude = 0.5F;
        assertEquals(500F,Logic.frequencyToHertz(frequency1,magnitude));
        assertEquals(0.5F,Logic.frequencyToHertz(frequency2,magnitude));
    }

    @Test
    void frequencyToPeriod() {
        Frequency frequency1 = Frequency.kilohertz;
        Frequency frequency2 = Frequency.hertz;

        float magnitude = 1F;
        assertEquals((1f/1000), Logic.frequencyToPeriod(magnitude,frequency1));
        assertEquals((1F), Logic.frequencyToPeriod(magnitude,frequency2));
    }


    @Test
    void calculateExecutionTime() {
        Program program = input.run();
        assertEquals(0.550000,Logic.calculateExecutionTime(program),0.0001);
    }

    @Test
    void calculateMipsRate() {
        Program program = input.run();
        assertEquals(0.029090,Logic.calculateMipsRate(program),0.0001);
    }
}