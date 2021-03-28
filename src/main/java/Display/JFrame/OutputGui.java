package Display.JFrame;

import Engine.OutputController;
import Entities.Program;

import javax.swing.*;
import java.awt.*;

public class OutputGui extends OutputController {

    private static OutputGuiBuilder builder;

    public static OutputGuiBuilder getBuilder() { return builder; }
    private static void setBuilder(OutputGuiBuilder buildWithProgram) { builder = buildWithProgram; }

    @Override
    public void DisplayOutput(Program program) {

        setBuilder(new OutputGuiBuilder(program, this));
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(OutputGui.getBuilder());
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}
