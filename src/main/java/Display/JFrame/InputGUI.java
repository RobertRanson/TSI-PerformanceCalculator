package Display.JFrame;

import Engine.InputController;
import Engine.RunCalculator;
import Entities.Program;
import Source.LoggingServiceInterface;

import javax.swing.*;
import java.awt.*;

import static Source.DataSourceConstants.*;

public class InputGUI implements InputController, LoggingServiceInterface {

    private static InputGuiBuilder builder;

    public static InputGuiBuilder getBuilder() { return builder; }
    private static void setBuilder(InputGuiBuilder buildWithProgram) { InputGUI.builder = buildWithProgram; }
    public static Program getProgram() { return program; }

    @Override
    public Program run() {

        this.setLogFile(SYSTEM_LOGS,SYSTEM_INPUT,false);

        setBuilder(new InputGuiBuilder(this));
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(InputGUI.getBuilder());
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
        return getProgram();
    }
}
