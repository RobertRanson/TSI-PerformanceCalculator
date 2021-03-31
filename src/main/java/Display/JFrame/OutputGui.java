package Display.JFrame;

import Engine.OutputController;
import Entities.Program;
import Source.LoggingServiceInterface;

import javax.swing.*;
import java.awt.*;

import static Source.DataSourceConstants.SYSTEM_LOGS;
import static Source.DataSourceConstants.SYSTEM_OUTPUT;

public class OutputGui implements OutputController, LoggingServiceInterface {

    private static OutputGuiBuilder builder;

    public static OutputGuiBuilder getBuilder() {
        return builder;
    }

    private static void setBuilder(OutputGuiBuilder buildWithProgram) {
        builder = buildWithProgram;
    }


    @Override
    public void run(Program program) {

        this.setLogFile(SYSTEM_LOGS, SYSTEM_OUTPUT, false);

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
