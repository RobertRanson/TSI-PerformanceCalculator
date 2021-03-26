package Display;

import Engine.RunCalculator;
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

public class InputGUI extends JPanel implements InputInterface {

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
    private Program allData;
    private boolean inputToFile;
    private AppDataSource.WriteToFile inputFile;

    public InputGUI() {
        this.setLayout(new BorderLayout());
        Dimension d = new Dimension(800, N_ROWS * table.getRowHeight() + 100);

        table.setPreferredScrollableViewportSize(d);
        for (int i = 0; i < N_ROWS; i++) { addRow(); }

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vScroll.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                isAutoScroll = !e.getValueIsAdjusting();
            }
        });
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel cpuinformation = new JPanel();
        cpuinformation.add(new JLabel("Clock Frequency: "));
        JTextField clockFrequency = new JTextField("0", 5);
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

                //File writer.
                getInputString(clockFrequency.getText() + ",");
                getInputString(buttonGroup.getSelection().getActionCommand() + ",");

                ArrayList<InstructionType> instructionTypes = new ArrayList<>();

                for (int i = 0; i < row + 1; i++) {
                    boolean checkBox = (boolean) table.getModel().getValueAt(i, 3);
                    if (checkBox) {

                        //File Writer
                        getInputString(String.valueOf(table.getModel().getValueAt(i, 0)) + ",");
                        getInputString(String.valueOf(table.getModel().getValueAt(i, 1)) + ",");
                        getInputString(String.valueOf(table.getModel().getValueAt(i, 2)));

                        instructionTypes.add(new InstructionType(
                                String.valueOf(table.getModel().getValueAt(i, 0)),
                                (Integer) table.getModel().getValueAt(i, 1),
                                (Integer) table.getModel().getValueAt(i, 2)));
                    }
                }
                getInputString("\n");
                Program program = new Program(
                        Float.valueOf(clockFrequency.getText()),
                        Frequency.valueOf(buttonGroup.getSelection().getActionCommand()),
                        instructionTypes);


                System.out.println(program.toString());
                System.out.println("CPI: " + Logic.calculateAverageCPI(program));
                System.out.println("MIPS: " + Logic.calculateMipsRate(program));
                System.out.println("Execution Time: " + Logic.calculateExecutionTime(program));
                allData = program;

                OutputInterface output = new OutputGUI();
                output.DisplayOutput(program, true, true);
            }
        }));
        this.add(panel, BorderLayout.SOUTH);


    }

    private void addRow() {
        dtm.addRow(new Object[]{
                String.valueOf("Type"),
                Integer.valueOf(row * 1000),
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


    public Program getAllData() {
        return allData;
    }

    @Override
    public void setInputToFile(boolean inputToFile, boolean appendToFile) {
        if (inputToFile) {
            inputFile = new AppDataSource.WriteToFile(USERACTION_FOLDER, INPUT_LOG, appendToFile);
            this.inputToFile = inputToFile;
        }
    }

    @Override
    public String getInputString(String message) {
        System.out.println(message);
        if (inputToFile) {
            inputFile.write(message);
        }
        return message;
    }

    @Override
    public int getInputInt() {
        return 0;
    }
}
