package com.github.pires.obd.reader.config;

import com.github.pires.obd.commands.CustomObdCommand;
import com.github.pires.obd.commands.DtcErrorsCommand;
import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.SpeedCommand;
import com.github.pires.obd.commands.control.DistanceMILOnCommand;
import com.github.pires.obd.commands.DtcNumberCommand;
import com.github.pires.obd.commands.control.EquivalentRatioCommand;
import com.github.pires.obd.commands.control.ModuleVoltageCommand;
import com.github.pires.obd.commands.control.TimingAdvanceCommand;
import com.github.pires.obd.commands.control.TroubleCodesCommand;
import com.github.pires.obd.commands.control.VinCommand;
import com.github.pires.obd.commands.engine.LoadCommand;
import com.github.pires.obd.commands.engine.MassAirFlowCommand;
import com.github.pires.obd.commands.engine.OilTempCommand;
import com.github.pires.obd.commands.engine.RPMCommand;
import com.github.pires.obd.commands.engine.RuntimeCommand;
import com.github.pires.obd.commands.engine.ThrottlePositionCommand;
import com.github.pires.obd.commands.fuel.AirFuelRatioCommand;
import com.github.pires.obd.commands.fuel.ConsumptionRateCommand;
import com.github.pires.obd.commands.fuel.FindFuelTypeCommand;
import com.github.pires.obd.commands.fuel.FuelLevelCommand;
import com.github.pires.obd.commands.fuel.FuelTrimCommand;
import com.github.pires.obd.commands.fuel.WidebandAirFuelRatioCommand;
import com.github.pires.obd.commands.pressure.BarometricPressureCommand;
import com.github.pires.obd.commands.pressure.FuelPressureCommand;
import com.github.pires.obd.commands.pressure.FuelRailPressureCommand;
import com.github.pires.obd.commands.pressure.IntakeManifoldPressureCommand;
import com.github.pires.obd.commands.temperature.AirIntakeTemperatureCommand;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureCommand;
import com.github.pires.obd.commands.temperature.EngineCoolantTemperatureCommand;
import com.github.pires.obd.enums.FuelTrim;

import java.util.ArrayList;

/**
 * TODO put description
 */

public final class ObdConfig {

    public static ArrayList<ObdCommand> getCommands(int config) {
        if(config == 0) {
            return getVIN();
        }
        else if(config == 1) {
            return getCommands();
        }
        else if(config == 3) {
            return getDTC();
        }
        else if(config == 4) {
            return getRPMAndVelocity();
        }
        else if(config == 5) {
            return deleteDTC();
        }
        else if(config == 6) {
            return getDiagnosis();
        }
        return getPids();
    }

    public static ArrayList<ObdCommand> getVIN() {
        ArrayList<ObdCommand> cmds = new ArrayList<>();

        // Only VIN
        cmds.add(new VinCommand());

        return cmds;
    }

    public static ArrayList<ObdCommand> getPids() {
        ArrayList<ObdCommand> cmds = new ArrayList<>();

        // 128 PIDS + VIN
        // cmds.add(new VinCommand()); // 09 02
        // for(int i = 0; i < 128; i++) {
        //     String pid = "01 " + ((i > 15) ? Integer.toHexString(i) : "0" + Integer.toHexString(i));
        //     cmds.add(new CustomObdCommand(pid));
        // }

        // 128 PIDS + VIN
        cmds.add(new VinCommand()); // 09 02
        cmds.add(new CustomObdCommand("01 00"));
        cmds.add(new CustomObdCommand("01 20"));
        cmds.add(new CustomObdCommand("01 40"));
        cmds.add(new CustomObdCommand("01 60"));
        cmds.add(new CustomObdCommand("01 80"));
        cmds.add(new CustomObdCommand("01 A0"));
        cmds.add(new CustomObdCommand("01 C0"));

        return cmds;
    }

    public static ArrayList<ObdCommand> getDTC() {
        ArrayList<ObdCommand> cmds = new ArrayList<>();

        //cmds.add(new VinCommand()); // 09 02
        // cmds.add(new CustomObdCommand("03 00"));
        //cmds.add(new CustomObdCommand("03"));
        // cmds.add(new CustomObdCommand("03 01"));
        cmds.add(new DtcNumberCommand());
        cmds.add(new DtcErrorsCommand());

        return cmds;
    }

    public static ArrayList<ObdCommand> deleteDTC() {
        ArrayList<ObdCommand> cmds = new ArrayList<>();

        // Only VIN
        cmds.add(new CustomObdCommand("04"));

        return cmds;
    }

    public static ArrayList<ObdCommand> getDiagnosis() {
        ArrayList<ObdCommand> cmds = new ArrayList<>();

        cmds.add(new VinCommand()); // 09 02
        cmds.add(new DtcNumberCommand());
        cmds.add(new DtcErrorsCommand());
        cmds.add(new SpeedCommand());
        cmds.add(new RPMCommand()); // 01 0C
        cmds.add(new ModuleVoltageCommand()); // 01 42
        cmds.add(new DistanceMILOnCommand()); //01 21
        cmds.add(new TimingAdvanceCommand()); // 01 0E
        cmds.add(new VinCommand()); // 09 02
        cmds.add(new LoadCommand()); // 01 04
        cmds.add(new MassAirFlowCommand()); // 01 10
        cmds.add(new ThrottlePositionCommand()); // 01 11
        cmds.add(new FindFuelTypeCommand()); //01 51
        cmds.add(new ConsumptionRateCommand()); // 01 5E
        cmds.add(new FuelLevelCommand()); // 01 2F
        cmds.add(new OilTempCommand());
        cmds.add(new BarometricPressureCommand());
        cmds.add(new FuelPressureCommand());
        cmds.add(new FuelRailPressureCommand());
        cmds.add(new IntakeManifoldPressureCommand());
        cmds.add(new AirIntakeTemperatureCommand());
        cmds.add(new AmbientAirTemperatureCommand());
        cmds.add(new EngineCoolantTemperatureCommand());

        return cmds;
    }

    public static ArrayList<ObdCommand> getRPMAndVelocity() {
        ArrayList<ObdCommand> cmds = new ArrayList<>();

        cmds.add(new SpeedCommand());
        cmds.add(new RPMCommand());

        return cmds;
    }

    public static ArrayList<ObdCommand> getCommands() {
        ArrayList<ObdCommand> cmds = new ArrayList<>();

        // Misc
        cmds.add(new SpeedCommand());
        cmds.add(new RPMCommand()); // 01 0C

        // Control
        cmds.add(new ModuleVoltageCommand()); // 01 42
        //cmds.add(new EquivalentRatioCommand()); //01 44
        cmds.add(new DistanceMILOnCommand()); //01 21
        //cmds.add(new DtcNumberCommand()); // 01 01
        cmds.add(new TimingAdvanceCommand()); // 01 0E
        //cmds.add(new TroubleCodesCommand()); // 03
        cmds.add(new VinCommand()); // 09 02

        // Engine
        cmds.add(new LoadCommand()); // 01 04

        //cmds.add(new RuntimeCommand()); //01 1F
        cmds.add(new MassAirFlowCommand()); // 01 10
        cmds.add(new ThrottlePositionCommand()); // 01 11

        // Fuel
        cmds.add(new FindFuelTypeCommand()); //01 51
        cmds.add(new ConsumptionRateCommand()); // 01 5E
        //cmds.add(new AverageFuelEconomyObdCommand());
        //cmds.add(new FuelEconomyCommand());
        cmds.add(new FuelLevelCommand()); // 01 2F
        // cmds.add(new FuelEconomyMAPObdCommand());
        // cmds.add(new FuelEconomyCommandedMAPObdCommand());
        // cmds.add(new FuelTrimCommand(FuelTrim.LONG_TERM_BANK_1));
        // cmds.add(new FuelTrimCommand(FuelTrim.LONG_TERM_BANK_2));
        // cmds.add(new FuelTrimCommand(FuelTrim.SHORT_TERM_BANK_1));
        // cmds.add(new FuelTrimCommand(FuelTrim.SHORT_TERM_BANK_2));
        // cmds.add(new AirFuelRatioCommand());
        //cmds.add(new WidebandAirFuelRatioCommand());
        cmds.add(new OilTempCommand());

        // Pressure
        cmds.add(new BarometricPressureCommand());
        cmds.add(new FuelPressureCommand());
        cmds.add(new FuelRailPressureCommand());
        cmds.add(new IntakeManifoldPressureCommand());

        // Temperature
        cmds.add(new AirIntakeTemperatureCommand());
        cmds.add(new AmbientAirTemperatureCommand());
        cmds.add(new EngineCoolantTemperatureCommand());

        return cmds;
    }

}
