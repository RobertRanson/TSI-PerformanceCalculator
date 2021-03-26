import Display.InputConsole;
import Display.InputGUI;
import Display.InputInt;
import Display.OutputGUI;
import Engine.*;
import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import EntitiesDataSourceMapping.DataBaseSetup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    private static void databaseSetup(){
        DataBaseSetup dataBaseSetup = new DataBaseSetup();
        dataBaseSetup.setup();
    }

    public static void main(String[ ] args) {
//        String result = this.input.getInputString();
//        this.output.output(result);
//        databaseSetup();
//        Engine.LogIn userLogIn = new Engine.LogIn();
//
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


//        Console -----
//        InputInt userInput = new InputConsole();
//
//
//        System.out.println("Enter the Clock Frequency: ");
//        float userInputClockFreq =  Float.parseFloat(userInput.getInputString());
//
//        System.out.println("[hertz, kilohertz, decahertz, megahertz, gigahertz]");
//        System.out.println("Enter the Units: ");
//        Frequency userInputFreqUnits = Frequency.valueOf(userInput.getInputString());
//
//        ArrayList<InstructionType> userInputInstructions = new ArrayList<>();
//
//        System.out.println("Enter the number of distict instruction types: ");
//        int userInputNumberOfInst = userInput.getInputInt();
//
//        for (int i = 0; i < userInputNumberOfInst; i++) {
//            System.out.println("------------------------------------");
//            System.out.println("Instruction: " + (i+1) + "/" + userInputNumberOfInst);
//            System.out.println("Enter the name of the Instruction: ");
//            String userInputType = userInput.getInputString();
//            System.out.println("Enter the instruction count: ");
//            int userInputInstCount = userInput.getInputInt();
//            System.out.println("Enter the cycles per instruction: ");
//            int userInputCPI = userInput.getInputInt();
//            InstructionType userInputInst = new InstructionType(userInputType,userInputInstCount,userInputCPI);
//            userInputInstructions.add(userInputInst);
//        }
//
//        Program userInputProgram = new Program(userInputClockFreq,userInputFreqUnits,userInputInstructions);
//        System.out.println("CPI:" + Logic.calculateAverageCPI(userInputProgram));
//        System.out.println("MIPS:" + Logic.calculateMipsRate(userInputProgram));
//        System.out.println("TIME:" + Logic.calculateExecutionTime(userInputProgram));

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                InputGUI nlt = new InputGUI();
                f.add(nlt);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
}

}
