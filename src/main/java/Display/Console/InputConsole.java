package Display.Console;

import Engine.InputController;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingServiceInterface;

import java.util.ArrayList;
import java.util.Scanner;

import static Source.DataSourceConstants.*;

public class InputConsole implements InputController, LoggingServiceInterface {

    private String delimiter = ",";
    private final Scanner userInput = new Scanner(System.in);

    @Override
    public Program run() {

        this.setLogFile(SYSTEM_LOGS,SYSTEM_INPUT,false);

        //CPU Information

        this.setClockFrequency(
                getInputString("Enter the Clock Frequency: "),
                getInputString("[hertz, kilohertz, decahertz, megahertz, gigahertz]\n" + "Enter the Units: "),
                this
        );


        //Instruction information

        int userInputNumberOfInst = getInputInt("Enter the number of district instruction types: ");

        ArrayList<InstructionType> userInputInstructions = new ArrayList<>();

        for (int i = 0; i < userInputNumberOfInst; i++) {
            System.out.println("------------------------------------");
            System.out.println("Instruction: " + (i+1) + "/" + userInputNumberOfInst);

            this.addInstruction(
                    getInputString("Enter the name of the Instruction: "),
                    getInputString("Enter the instruction count: "),
                    getInputString("Enter the cycles per instruction: "),
                    this
            );
        }

        return (this.program);

    }

    public String getInputString(String message){
        System.out.println(message);
        String result = userInput.nextLine();
        return result;
    }

    public int getInputInt(String message) {
        int result = Integer.parseInt(getInputString(message));
        return result;
    }
}
