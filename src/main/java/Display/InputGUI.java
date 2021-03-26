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

public class InputGUI extends JPanel {

    private static final int N_ROWS = 1;
    private static String[] header = {"Type", "Instruction Count", "CPI", "Include in calculation"};
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

    public InputGUI() {
        this.setLayout(new BorderLayout());
        Dimension d = new Dimension(800, N_ROWS * table.getRowHeight() + 100);
        table.setPreferredScrollableViewportSize(d);
        for (int i = 0; i < N_ROWS; i++) {
            addRow();
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

        JPanel cpuinformation = new JPanel();
        cpuinformation.add(new JLabel("Clock Frequency: "));
        JTextField clockFrequency = new JTextField("0",5);
        cpuinformation.add(clockFrequency);

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton r1 = new JRadioButton("hertz");
        JRadioButton r2 = new JRadioButton("decahertz");
        JRadioButton r3 = new JRadioButton("kilohertz");
        JRadioButton r4 = new JRadioButton("megahertz");
        JRadioButton r5 = new JRadioButton("gighertz");

        r1.setActionCommand("hertz");
        r2.setActionCommand("decahertz");
        r3.setActionCommand("kilohertz");
        r4.setActionCommand("megahertz");
        r5.setActionCommand("gighertz");

        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);
        buttonGroup.add(r4);
        buttonGroup.add(r5);

        cpuinformation.add(r1);
        cpuinformation.add(r2);
        cpuinformation.add(r3);
        cpuinformation.add(r4);
        cpuinformation.add(r5);

        this.add(cpuinformation, BorderLayout.NORTH);


        JPanel panel = new JPanel();
        panel.add(new JButton(new AbstractAction("Add Row") {

            @Override
            public void actionPerformed(ActionEvent e) {
                row++;
                addRow();
            }
        }));
        panel.add(new JButton(new AbstractAction("Calculate") {

            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(table.getModel().getValueAt(0,3));
                ArrayList<InstructionType> instructionTypes = new ArrayList<>();
                System.out.println("Rows: " + row);
                for (int i = 0; i < row+1; i++) {
                    boolean checkBox = (boolean) table.getModel().getValueAt(i,3);
//                    System.out.println((Integer) table.getModel().getValueAt(i,1));
                    if(checkBox){
                        instructionTypes.add(new InstructionType(
                                String.valueOf(table.getModel().getValueAt(i,0)),
                                (Integer) table.getModel().getValueAt(i,1),
                                (Integer) table.getModel().getValueAt(i,2)));
                    }
                }
                Program program = new Program(
                        Float.valueOf(clockFrequency.getText()),
                        Frequency.valueOf(buttonGroup.getSelection().getActionCommand()),
                        instructionTypes);

                System.out.println(program.toString());
                System.out.println("CPI: " + Logic.calculateAverageCPI(program));
                System.out.println("MIPS: " + Logic.calculateMipsRate(program));
                OutputGUI.DisplayOutput(program);

            }
        }));
        this.add(panel, BorderLayout.SOUTH);




    }

    private void addRow() {

        dtm.addRow(new Object[]{
                String.valueOf("Type"),
                Integer.valueOf(row*1000),
                Integer.valueOf(row),
                Boolean.valueOf(false)
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                InputGUI nlt = new InputGUI();
                f.add(nlt);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}
