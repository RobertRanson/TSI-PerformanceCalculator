# Computer performance calculator
### COMPSCI1022 Testing and Software Improvement
#### Inputs
* Clock Speed
* Clock speed unit
* Instructions
  * Name
  * Instruction count
  * Cycles per instruction

![GUI Screenshot](https://i.imgur.com/rvE25kM.png)
    
#### Outputs
* Clock Speed Hz
* Total instruction count
* Instructions
    * Name
    * Instruction count
    * Cycles per instruction
    * Instruction Execution time
* Average Cycles per instruction
* MIPS (Million instructions per second) rate
* Program execution time

![GUI Screenshot](https://i.imgur.com/rUfgFWM.png)

## Source input/output
* Console
* JFrame GUI
* txt file

## Adapter design pattern
* Display.Files.TestOutput
* Display.Files.InputStub

## Doubling Stub and Unit tests
* Display.Files.InputStub
* LogicTest

## Doubling Mock
* InputLogsTest

## Doubling Fake
* resource.SystemLogs.systemInputLog
* resource.SystemLogs.systemOutputLog
* Display.Files.TestOutput
* FakeTest
