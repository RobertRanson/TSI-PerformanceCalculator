package Engine;

import Display.Files.InputStub;
import Engine.InputController;
import Engine.Logic;
import Entities.Frequency;
import Entities.Program;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
This class contains unit tests for the core logic of the program
It uses the stub class InputStub which implements the
InputController interface.

 */

class LogicTests {


    Program getProgram(){
        InputController input = new InputStub();
        Program program = input.run();
        return program;
    }


    @Test
    void calculateAverageCPITest() {
        double cpi = Logic.calculateAverageCPI(getProgram());
        assertEquals(1.375,cpi);
    }


    @Test
    void frequencyToHertzTest() {
        Frequency frequency1 = Frequency.kilohertz;
        Frequency frequency2 = Frequency.hertz;

        double magnitude = 0.5d;
        assertEquals(500d,Logic.frequencyToHertz(frequency1,magnitude));
        assertEquals(0.5d,Logic.frequencyToHertz(frequency2,magnitude));
    }

    @Test
    void frequencyToPeriodTest() {
        Frequency frequency1 = Frequency.kilohertz;
        Frequency frequency2 = Frequency.hertz;

        double magnitude = 1d;
        assertEquals((1d/1000), Logic.frequencyToPeriod(magnitude,frequency1));
        assertEquals((1d), Logic.frequencyToPeriod(magnitude,frequency2));
    }


    @Test
    void calculateExecutionTimeTest() {
        assertEquals(0.550000,Logic.calculateExecutionTime(getProgram()),0.0001);
    }

    @Test
    void calculateMipsRateTest() {
        assertEquals(0.029090,Logic.calculateMipsRate(getProgram()),0.0001);
    }
}