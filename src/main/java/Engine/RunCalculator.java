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

    public RunCalculator(EnumSource displayMethod) throws InterruptedException {
        this.displayMethod = displayMethod;

        switch (displayMethod) {
            case FILES:
                InputLogs inputLogs = new InputLogs();
                inputLogs.loggingSettings(true, false);

                OutputLogs outputLogs = new OutputLogs();
                outputLogs.loggingSettings(true,false);

                program = inputLogs.DisplayOutput();
                outputLogs.DisplayOutput(program);

                break;

            case CONSOLE:
                InputConsole input = new InputConsole();
                OutputConsole output = new OutputConsole();

                input.loggingSettings(true, false);
                output.loggingSettings(true, false);

                program = input.DisplayOutput();
                output.DisplayOutput(program);

                break;

            case GUI:
                InputGUI inputGui = new InputGUI();
                OutputGui outputGUI = new OutputGui();

                inputGui.loggingSettings(true, false);
                outputGUI.loggingSettings(true, false);

                /*
                Call .DisplayOut for GUI

                Wait for 'Calculate' event
                method returns program
                program = inputGui.DisplayOutput();

                //todo factory design pattern?
                //todo logging

                */


                program = inputGui.DisplayOutput();

//                System.out.println("Wait begin");
//                while (program==null){
//                    System.out.println("waiting");
//                    wait(2000);
//                }
//
//                System.out.println("wait end");
//
//                outputGUI.DisplayOutput(program);


//                Program program = syncInput(inputGui);
//                syncOutput(outputGUI, program);

                break;
        }
    }

    public Program syncInput(InputGUI inputGUI) throws InterruptedException {
        synchronized (this) {
            Program program = inputGUI.DisplayOutput();
//            System.out.println("Notify");
//            this.notify();
            return program;
        }
    }

    public void syncOutput(OutputGui outputGui, Program program) throws InterruptedException {
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Wait begin");

            wait();
            System.out.println("wait end");

            outputGui.DisplayOutput(program);
        }
    }
}
