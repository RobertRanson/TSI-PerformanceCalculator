package Display.Console;

import java.util.ArrayList;

/*
This is a class that fakes InputConsole.
it overwrites InputConsole.getString()
which is used in InputConsole to read from the terminal.
 */

public class FakeInputConsole extends InputConsole {

    ArrayList<String> fakeInputs = new ArrayList<>();

    public FakeInputConsole() {
        fakeInputs.add("40");
        fakeInputs.add("decahertz");
        fakeInputs.add("2");
        fakeInputs.add("Logic");
        fakeInputs.add("10000");
        fakeInputs.add("2");
        fakeInputs.add("Branch");
        fakeInputs.add("4000");
        fakeInputs.add("4");
        fakeInputs.add("2");
        fakeInputs.add("2");

    }

    public String getInputString(String message) {
        return fakeInputs.remove(0);
    }

    public int getInputInt(String message) {
        return Integer.valueOf(getInputString(message));
    }
}
