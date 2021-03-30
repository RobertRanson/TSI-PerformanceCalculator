package Display.Files;

import Engine.InputController;
import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;

import java.util.ArrayList;

public class InputStub implements InputController {
    @Override
    public Program run() {
        InstructionType inst1 = new InstructionType(
                "Arith",
                10000,
                1);
        InstructionType inst2 = new InstructionType(
                "Float",
                6000,
                2);
        ArrayList<InstructionType> instList =  new ArrayList<InstructionType>(){};
        instList.add(inst1);
        instList.add(inst2);

        return (new Program(
                40.0F,
                Frequency.kilohertz,
                instList));
    }
}
