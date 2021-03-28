package Display.Console;

import Engine.InputController;
import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;

import java.util.ArrayList;
import java.util.Scanner;

public class InputConsole extends InputController {

    private String delimiter = ",";
    private final Scanner userInput = new Scanner(System.in);

    @Override
    public Program DisplayOutput() {

        float userInputClockFreq =  (Float.parseFloat(getInputString("Enter the Clock Frequency: ")));
        System.out.println("[hertz, kilohertz, decahertz, megahertz, gigahertz]");
        Frequency userInputFreqUnits = Frequency.valueOf(getInputString("Enter the Units: "));

        System.out.println("Enter the number of district instruction types: ");
        int userInputNumberOfInst = getInputInt();

        ArrayList<InstructionType> userInputInstructions = new ArrayList<>();

        for (int i = 0; i < userInputNumberOfInst; i++) {

            System.out.println("------------------------------------");
            System.out.println("Instruction: " + (i+1) + "/" + userInputNumberOfInst);
            String userInputType = getInputString("Enter the name of the Instruction: ");
            System.out.println("Enter the instruction count: ");
            int userInputInstCount = getInputInt();
            System.out.println("Enter the cycles per instruction: ");
            int userInputCPI = getInputInt();

            userInputInstructions.add(new InstructionType(userInputType,userInputInstCount,userInputCPI));
        }

        logEvent("\n");
        return (new Program(userInputClockFreq,userInputFreqUnits,userInputInstructions));

    }

    public String getInputString(String message){
        System.out.println(message);
        String result = userInput.nextLine();
        logEvent(result+delimiter);
        return result;
    }

    public int getInputInt() {
        int result = Integer.parseInt(userInput.nextLine());
        logEvent(String.valueOf(result));
        return result;
    }


}
