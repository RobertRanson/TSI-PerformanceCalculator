package Engine;

import AppDataSource.DBSetup;
import Entities.Customer;
import EntitiesDataSourceMapping.CustomerDatabaseMapping;

public class LogIn {

    Display.OutputInterface output = new Display.OutputConsole();
    Display.InputInt input = new Display.InputConsole();

    public void setOutput(Display.OutputInterface output,boolean outputWriteToFile, boolean appendToFile){
        if (outputWriteToFile) {
            output.setOutputToFile( outputWriteToFile , appendToFile);
        }
        this.output = output;
    }

    public void setInput(Display.InputInt input,boolean inputWriteToFile, boolean appendToFile){
        if (inputWriteToFile) {
            input.setInputToFile( inputWriteToFile , appendToFile);
        }
        this.input = input;
    }

    public void logIn(){
        CustomerDatabaseMapping customerDatabaseMapping = new CustomerDatabaseMapping();
        output.setOutputToFile(true, false);
        output.output("Enter email address?");
        input.setInputToFile(true, false);
        String result = input.getInputString();
        Customer customer = customerDatabaseMapping.getCustomerFromEmail(result);
        System.out.println(customer.getPassword());
        System.out.println("Check if user exists and has same password");
    }
}
