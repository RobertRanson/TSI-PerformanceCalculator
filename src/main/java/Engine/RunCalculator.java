package Engine;

import Display.Console.InputConsole;
import Display.JFrame.InputGUI;
import Display.Files.InputLogs;
import Display.Console.OutputConsole;
import Display.JFrame.OutputGui;
import Display.Files.OutputLogs;
import Entities.Program;

public class RunCalculator {
    public EnumSource displayMethod;
    private Program program = null;

    public RunCalculator(EnumSource displayMethod) {
        this.displayMethod = displayMethod;

        InputController inputController;
        OutputController outputController;


        switch (displayMethod) {
            case FILES:
                inputController = new InputLogs();
                outputController = new OutputLogs();
                break;
            case CONSOLE:
                inputController = new InputConsole();
                outputController = new OutputConsole();
                break;
            case GUI:
                inputController  = new InputGUI();
                outputController = new OutputGui();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + displayMethod);
        }

        program = inputController.run();
        try {
            outputController.run(program);
        }catch (Exception e){
            System.out.println("outputController.run: "+e);
        }
    }
}
