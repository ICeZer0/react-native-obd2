package com.github.pires.obd.commands;

public class CustomObdCommand extends ObdCommand{

    public CustomObdCommand(String command) {
        super(command);
    }

    @Override
    protected void performCalculations() {

    }

    @Override
    public String getFormattedResult() {
        return this.rawData;
    }

    @Override
    public String getCalculatedResult() {
        return this.rawData;
    }

    @Override
    public String getName() {
        return this.cmd;
    }
}
