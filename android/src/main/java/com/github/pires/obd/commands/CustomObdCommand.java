package com.github.pires.obd.commands;

import android.util.Log;

public class CustomObdCommand extends ObdCommand{

    private static final String TAG = "CustomObdCommand";

    public CustomObdCommand(String command) {
        super(command);
        Log.e(TAG, "PID: " + this.cmd);
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
