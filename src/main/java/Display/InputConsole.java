package Display;

import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import static AppDataSource.DataSourceConstants.INPUT_LOG;
import static AppDataSource.DataSourceConstants.USERACTION_FOLDER;

public class InputConsole implements Display.InputInterface {
    private Scanner userInput = new Scanner(System.in);
    private boolean inputToFile = false;

    private AppDataSource.WriteToFile inputFile;

    public void setInputToFile(boolean inputToFile, boolean appendToFile){
        if (inputToFile){
            inputFile = new AppDataSource.WriteToFile(USERACTION_FOLDER, INPUT_LOG, appendToFile);
        }
        this.inputToFile = inputToFile;
    }




    public Program getAllData() {

        float userInputClockFreq =  (Float.parseFloat(getInputString("Enter the Clock Frequency: ")));
        System.out.println("[hertz, kilohertz, decahertz, megahertz, gigahertz]");
        Frequency userInputFreqUnits = Frequency.valueOf(getInputString("Enter the Units: "));

        ArrayList<InstructionType> userInputInstructions = new ArrayList<>();

        System.out.println("Enter the number of distict instruction types: ");
        int userInputNumberOfInst = getInputInt();

        for (int i = 0; i < userInputNumberOfInst; i++) {
            System.out.println("------------------------------------");
            System.out.println("Instruction: " + (i+1) + "/" + userInputNumberOfInst);
            String userInputType = getInputString("Enter the name of the Instruction: ");
            System.out.println("Enter the instruction count: ");
            int userInputInstCount = getInputInt();
            System.out.println("Enter the cycles per instruction: ");
            int userInputCPI = getInputInt();

            InstructionType userInputInst = new InstructionType(userInputType,userInputInstCount,userInputCPI);
            userInputInstructions.add(userInputInst);
        }
        getInputString("\n");

        Program userInputProgram = new Program(userInputClockFreq,userInputFreqUnits,userInputInstructions);
        return userInputProgram;
    }

    public String getInputString(String message){
        System.out.println(message);
        String result = userInput.nextLine();
        if (inputToFile) {
            inputFile.write(result);
        }
        return result;
    }

    public int getInputInt() {
        int result = Integer.parseInt(userInput.nextLine());
        if (inputToFile) {
            inputFile.write(String.valueOf(result));
        }
        return result;
    }

}
