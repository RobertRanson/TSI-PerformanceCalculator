import Engine.GuiOrConsole;
import Engine.RunCalculator;

public class Main {

    public static void main(String[ ] args) {

        RunCalculator execution = new RunCalculator(GuiOrConsole.CONSOLE);

        // ---- hardcoded
//        InstructionType inst1 = new InstructionType("IntArith",45000,1);
//        InstructionType inst2 = new InstructionType("DataTrans",32000,2);
//        InstructionType inst3 = new InstructionType("FloatPoint",15000,2);
//        InstructionType inst4 = new InstructionType("ControlTrans",8000,2);
//
//        ArrayList<InstructionType> instructionTypes = new ArrayList<>();
//        instructionTypes.add(inst1);
//        instructionTypes.add(inst2);
//        instructionTypes.add(inst3);
//        instructionTypes.add(inst4);
//
//        float clockFrequency = 40;
//        Frequency units = Frequency.megahertz;
//
//        Program program = new Program(clockFrequency,units,instructionTypes);
//
//        System.out.println("CPI:" + Logic.calculateAverageCPI(program));
//        System.out.println("MIPS:" + Logic.calculateMipsRate(program));
//        System.out.println("TIME:" + Logic.calculateExecutionTime(program));
//
//        OutputGUI.DisplayOutput(program);
//        System.out.println(program.toString());

}

}
