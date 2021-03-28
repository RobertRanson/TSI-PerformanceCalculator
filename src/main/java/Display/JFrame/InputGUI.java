package Display.JFrame;

import Engine.InputController;
import Engine.RunCalculator;
import Entities.Program;

import javax.swing.*;
import java.awt.*;

public class InputGUI extends InputController {

    private static Program program = null;
    private static InputGuiBuilder builder;
    private static RunCalculator syncLock;

    public static RunCalculator getSyncLock() { return syncLock; }
    public static void setSyncLock(RunCalculator syncLock) { InputGUI.syncLock = syncLock; }

    public static Program getProgram() { return program; }
    public static void setProgram(Program program) { InputGUI.program = program; }

    public static InputGuiBuilder getBuilder() { return builder; }
    private static void setBuilder(InputGuiBuilder buildWithProgram) { InputGUI.builder = buildWithProgram; }

    @Override
    public Program DisplayOutput() {

        setSyncLock(syncLock);
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
