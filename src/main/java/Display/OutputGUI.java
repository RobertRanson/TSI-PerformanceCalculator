package Display;

import Engine.Logic;
import Entities.InstructionType;
import Entities.Program;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class OutputGUI extends JPanel {
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


    public static void DisplayOutput(Program program){
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                OutputGUI nlt = new OutputGUI(program);
                f.add(nlt);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }


    public OutputGUI(Program program) {
        this.setLayout(new BorderLayout());
        Dimension d = new Dimension(800, N_ROWS * table.getRowHeight() + 100);
        table.setPreferredScrollableViewportSize(d);
        for (InstructionType inst : program.getInstructions()) {
            dtm.addRow(new Object[]{
                    inst.getType(),
                    inst.getInstructionCount(),
                    inst.getCyclesPerInstruction(),
                    inst.getExecutionTime()
            });
        }
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vScroll.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                isAutoScroll = !e.getValueIsAdjusting();
            }
        });
        this.add(scrollPane, BorderLayout.CENTER);


        JPanel cpuInformation = new JPanel();
        cpuInformation.setLayout(new BoxLayout(cpuInformation,BoxLayout.PAGE_AXIS));

        JLabel l1 = new JLabel("Clock Frequency: " + program.getClockFrequency() + " (Hz)");
        cpuInformation.add(l1);
        cpuInformation.add(new JLabel("Instruction Count: " + program.getTotalInstructionCount()));

        cpuInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
        cpuInformation.setBorder(BorderFactory.createTitledBorder("CPU STATS"));
        this.add(cpuInformation, BorderLayout.NORTH);

        JPanel results = new JPanel();
        results.setLayout(new BoxLayout(results,BoxLayout.PAGE_AXIS));

        results.add(new JLabel("Average CPI: " + Logic.calculateAverageCPI(program)));
        results.add(new JLabel("MIPS: " + Logic.calculateMipsRate(program)));
        results.add(new JLabel("Execution Time: " + Logic.calculateExecutionTime(program) + " (s)"));

        results.setAlignmentX(Component.CENTER_ALIGNMENT);
        results.setBorder(BorderFactory.createTitledBorder("RESULTS"));
        this.add(results, BorderLayout.SOUTH);
    }
}
