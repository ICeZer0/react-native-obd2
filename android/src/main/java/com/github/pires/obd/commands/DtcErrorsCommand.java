package com.github.pires.obd.commands;

import android.util.Log;

public class DtcErrorsCommand extends ObdCommand{

    private static final String TAG = "DtcErrorsCommand";
    private String jsonCodes = "[]";

    public DtcErrorsCommand() {
        super("03");
        Log.e(TAG, "PID: " + this.cmd);
    }

    @Override
    protected void performCalculations() {
        Log.e("DTCErrors", "---> " + this.buffer.toString());
        //this.rawData = this.buffer.toString();
        StringBuilder sbCodes = new StringBuilder();
        sbCodes.append("[");

        for(int i = 1; i < this.buffer.size(); i+=2) {
            if(i % 7 == 0) i++;

            int b1 = 0, b2 = 0;
            b1 = this.buffer.get(i);
            if((i + 1) < this.buffer.size()) {
                b2 = this.buffer.get(i + 1);
            }

            if(b1 == 0 && b2 == 0) continue;

            sbCodes.append("\"P");
            sbCodes.append(String.format("%2x", b1).replace(' ', '0'));
            sbCodes.append(String.format("%2x", b2).replace(' ', '0'));
            sbCodes.append("\",");
        }
        sbCodes.setLength(sbCodes.length() - 1);

        sbCodes.append("]");
        this.jsonCodes = sbCodes.toString();
    }

    @Override
    public String getFormattedResult() {
        return this.jsonCodes;
    }

    @Override
    public String getCalculatedResult() {
        return this.jsonCodes;
    }

    @Override
    public String getName() {
        return this.cmd;
    }
}
