package Display;

public abstract class InputInt {

    public abstract String getInputString();

    public abstract void setInputToFile(boolean inputToFile, boolean appendToFile);

    public int getInputInt() {
        int result;
        result = -1;
        do {
            try {
                result = Integer.parseInt(this.getInputString().trim());
            } catch (NumberFormatException exception) {
                // Output expected NumberFormatException.
                System.out.println("Please enter a integer");
            }
        } while (result < 0);

        return result;
    }
}
