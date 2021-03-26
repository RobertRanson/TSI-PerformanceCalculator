package Display;

import Entities.Frequency;
import Entities.InstructionType;
import Engine.Logic;
import Entities.Program;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;


import static AppDataSource.DataSourceConstants.*;

public class InputGUI extends InputController{

    private static Program program = null;
    private static InputGuiBuilder builder;

    public static Program getProgram() { return program; }
    public static void setProgram(Program program) { InputGUI.program = program; }

    public static InputGuiBuilder getBuilder() { return builder; }
    private static void setBuilder(InputGuiBuilder buildWithProgram) { InputGUI.builder = buildWithProgram; }

    @Override
    public Program DisplayOutput() {

        setBuilder(new InputGuiBuilder(getProgram(),this));
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
