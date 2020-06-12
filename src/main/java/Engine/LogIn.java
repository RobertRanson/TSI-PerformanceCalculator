package Engine;

import AppDataSource.DBSetup;
import Entities.Customer;

public class LogIn {

    Display.OutputInterface output = new Display.OutputConsole();
    Display.InputInterface input = new Display.InputConsole();

    public void setOutput(Display.OutputInterface output,boolean outputWriteToFile, boolean appendToFile){
        if (outputWriteToFile) {
            output.setOutputToFile( outputWriteToFile , appendToFile);
        }
        this.output = output;
    }

    public void setInput(Display.InputInterface input,boolean inputWriteToFile, boolean appendToFile){
        if (inputWriteToFile) {
            input.setInputToFile( inputWriteToFile , appendToFile);
        }
        this.output = output;
    }

    public void logIn(){
        output.setOutputToFile(true, false);
        output.output("Enter email address and password");
        input.setInputToFile(true, false);
        String result = input.getInputString();
    }
}
