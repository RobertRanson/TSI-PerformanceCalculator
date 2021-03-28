package Display.Files;

import Engine.InputController;
import Entities.Frequency;
import Entities.InstructionType;
import Entities.Program;
import Source.LoggingService;

import java.util.ArrayList;

public class InputLogs extends InputController {

    private LoggingService loggingService;
    private String deliminator = ",";

    private void setLoggingService(LoggingService ls) { loggingService = ls; }

    @Override
    public Program DisplayOutput() {
        setLoggingService(LoggingService.getInstance());
        loggingService.setLogFile("UserActions/", "FileInput.txt",true);

        ArrayList<String> data = (ArrayList<String>) loggingService.getLogData("UserActions/", "FileInput.txt");

        float clockFrequency = Float.parseFloat(data.get(0));
        logEvent(clockFrequency+deliminator);

        Frequency frequencyUnits = Frequency.valueOf(data.get(1));
        logEvent(frequencyUnits+deliminator);

        int numberOfInstructions = Integer.valueOf(data.get(2));
        logEvent(numberOfInstructions+deliminator);

        ArrayList<InstructionType> fileInputInstructions = new ArrayList<>();

        for (int i = 3; i < data.size(); i=i+3) {
            String instType = data.get(i);
            int instCount = Integer.valueOf(data.get(i+1));
            int instCpi = Integer.valueOf(data.get(i+2));
            logEvent(instType+deliminator);
            logEvent(instCount+deliminator);
            logEvent(instCpi+deliminator);

            fileInputInstructions.add(new InstructionType(instType,instCount,instCpi));
        }

        return (new Program(clockFrequency,frequencyUnits,fileInputInstructions));
    }
}
