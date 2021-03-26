package Engine;

import Display.*;
import Entities.Program;

import javax.swing.*;
import java.awt.*;

public class RunCalculator {
    public GuiOrConsole displayMethod = GuiOrConsole.CONSOLE;
    public static InputInterface input;
    public static OutputInterface output;



    public RunCalculator(GuiOrConsole displayMethod) {
        this.displayMethod = displayMethod;

        switch (displayMethod) {
            case CONSOLE:
                input = new InputConsole();
                output = new OutputConsole();

                Program program = input.getAllData();


                output.DisplayOutput(program, true, true);
                break;
            case GUI:

                output = new OutputGUI();

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame f = new JFrame();
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        InputGUI nlt = new InputGUI();
                        nlt.setInputToFile(true, true);
                        f.add(nlt);
                        f.pack();
                        f.setLocationRelativeTo(null);
                        f.setVisible(true);

                    }
                });
                break;

        }
    }
}
