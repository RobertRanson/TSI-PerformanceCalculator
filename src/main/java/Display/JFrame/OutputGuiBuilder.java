package Display.JFrame;

import Engine.Logic;
import Entities.InstructionType;
import Entities.Program;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class OutputGuiBuilder extends JPanel {
    private static final int N_ROWS = 1;
    private static String[] header = {"Type", "Instruction Count", "CPI", "Execution Time"};
    private DefaultTableModel dtm = new DefaultTableModel(null, header) {

        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }
    };

    private JTable table = new JTable(dtm);
    private JScrollPane scrollPane = new JScrollPane(table);
    private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
    private int row;
    private boolean isAutoScroll;

    public OutputGuiBuilder(Program program, OutputGui outputGUI) {
        this.setLayout(new BorderLayout());


        //CPU Information

        JPanel cpuInformation = new JPanel();
        cpuInformation.setLayout(new BoxLayout(cpuInformation, BoxLayout.PAGE_AXIS));

        cpuInformation.add(new JLabel("Clock Frequency: " + outputGUI.getClockFrequency(program,outputGUI) + " (Hz)"));
        cpuInformation.add(new JLabel("Instruction Count: " + outputGUI.getTotalInstructionCount(program,outputGUI)));

        cpuInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
        cpuInformation.setBorder(BorderFactory.createTitledBorder("CPU STATS"));
        this.add(cpuInformation, BorderLayout.NORTH);

        //Instruction Information

        Dimension d = new Dimension(800, N_ROWS * table.getRowHeight() + 100);
        table.setPreferredScrollableViewportSize(d);

        for (InstructionType inst : program.getInstructions()) {
            dtm.addRow(new Object[]{
                    outputGUI.getInstructionType(inst,outputGUI),
                    outputGUI.getInstructionCount(inst,outputGUI),
                    outputGUI.getInstructionCpi(inst,outputGUI),
                    outputGUI.getInstructionExec(program,inst,outputGUI)
            });
        }

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vScroll.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                isAutoScroll = !e.getValueIsAdjusting();
            }
        });
        this.add(scrollPane, BorderLayout.CENTER);


        //Results

        JPanel results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.PAGE_AXIS));

        results.add(new JLabel("Average CPI: " + outputGUI.getAverageCpi(program,outputGUI)));
        results.add(new JLabel("MIPS: " + outputGUI.getMipsRate(program,outputGUI)));
        results.add(new JLabel("Execution Time: " + outputGUI.getExecTime(program,outputGUI) + " (s)"));

        results.setAlignmentX(Component.CENTER_ALIGNMENT);
        results.setBorder(BorderFactory.createTitledBorder("RESULTS"));
        this.add(results, BorderLayout.SOUTH);
    }
}

