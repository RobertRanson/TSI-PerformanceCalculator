package Engine;

import Display.*;
import Entities.Program;

import javax.swing.*;
import java.awt.*;

public class RunCalculator {
    public GuiOrConsole displayMethod = GuiOrConsole.CONSOLE;
    Program program;

    public RunCalculator(GuiOrConsole displayMethod) {
        this.displayMethod = displayMethod;

        switch (displayMethod) {
            case CONSOLE:
                InputConsole input = new InputConsole();
                OutputConsole output = new OutputConsole();

                input.loggingSettings(true,false);
                output.loggingSettings(true,false);

                program =  input.DisplayOutput();
                output.DisplayOutput(program);

                break;
            case GUI:

                InputGUI inputGui = new InputGUI();
                OutputGui outputGUI = new OutputGui();

                try {
                    program = inputGui.DisplayOutput();
                    System.out.println("---try");
                }finally {
                    outputGUI.DisplayOutput(program);
                    System.out.println("---finally");
                }
                break;
        }
    }
}
