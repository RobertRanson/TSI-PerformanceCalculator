package Entities;



import Engine.Logic;
import java.util.ArrayList;

public class Program {

    private float clockFrequency;
    private int totalInstructionCount;
    private ArrayList<InstructionType> instructions = new ArrayList<InstructionType>();

    public Program() {}
    public Program(float clockFrequency, Frequency units, ArrayList<InstructionType> instructions){
        setClockFrequency(clockFrequency,units);
        setInstructions(instructions);
        calculateTotalInstructionCount();
    }

    public void addInstruction(InstructionType instructionType){
        instructions.add(instructionType);
    }
    public void removeInstruction(InstructionType instructionType){
        instructions.remove(instructionType);
    }

    public void setInstructions(ArrayList<InstructionType> instructions){
        this.instructions = instructions;
    }
    public ArrayList<InstructionType> getInstructions() { return instructions; }

    public float getClockFrequency() { return clockFrequency; }
    public void setClockFrequency(float clockFrequency, Frequency units) { this.clockFrequency = Logic.frequencyToHertz(units,clockFrequency); }

    public void setTotalInstructionCount(int totalInstructionCount) { this.totalInstructionCount = totalInstructionCount; }

    public void calculateTotalInstructionCount(){
        int total = 0;
        for (InstructionType instruction : this.instructions) {
            total += instruction.getInstructionCount();
        }
        setTotalInstructionCount(total);
    }
    public int getTotalInstructionCount() {
        calculateTotalInstructionCount();
        return totalInstructionCount;
    }

    public String toString(){
        String output = "---- CPU STATS ----";
        output += "\nFrequency: ";
        output += String.valueOf(getClockFrequency());
        output += "\nInstruction Count: ";
        output += String.valueOf(getTotalInstructionCount());
        output += "\n---- INSTRUCTIONS ----";
        for (InstructionType inst: getInstructions()) {
            output += "\n" + inst.toString();
        }
        return output;
    }
}
