package Entities;

import java.util.ArrayList;

public class InstructionType {

    private String type;
    private int instructionCount;
    private int cyclesPerInstruction;
    private double executionTime;

    public InstructionType(String type, int instructionCount, int cyclesPerInstruction){
        setType(type);
        setInstructionCount(instructionCount);
        setCyclesPerInstruction(cyclesPerInstruction);
    }

    public void calculateExecutionTime(double clockFrequency){
        setExecutionTime((clockFrequency * this.cyclesPerInstruction * this.instructionCount));
    }

    public double getExecutionTime() { return executionTime; }
    public void setExecutionTime(double executionTime) { this.executionTime = executionTime; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getInstructionCount() { return instructionCount; }
    public void setInstructionCount(int instructionCount) { this.instructionCount = instructionCount; }

    public int getCyclesPerInstruction() { return cyclesPerInstruction; }
    public void setCyclesPerInstruction(int cyclesPerInstruction) { this.cyclesPerInstruction = cyclesPerInstruction; }

    public ArrayList<String> getinstructioninfo(){
        ArrayList<String> output = new ArrayList<>();

        output.add("Type: " + getType() );
        output.add("Instruction Count: " + getInstructionCount());
        output.add("CPI: " + getCyclesPerInstruction());
        output.add("Execution time: " + getExecutionTime());

        return output;
    }
    public String toString(){
        return (        "\nType: " + getType() +
                        "\nInstruction Count: " + getInstructionCount() +
                        "\nCPI: " + getCyclesPerInstruction()+
                        "\nExecution time: " + getExecutionTime());
    }
}
