package Engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputControllerTest {

    @Test

    void stringToFloat() {

        InputController inputController = null;
        float aswer = inputController.stringToFloat("s");
        assertEquals(0.5F,aswer);

    }
}