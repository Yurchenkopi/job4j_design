package ru.job4j.ood.srp.ex2;

import java.util.List;

public class PressureGaugeMonitor implements PressureGaugeReader {

    private final PressureGaugeModel<String> pressureGaugeModel;

    public PressureGaugeMonitor(PressureGaugeModel<String> pressureGaugeModel) {
        this.pressureGaugeModel = pressureGaugeModel;
    }

    @Override
    public double readPressure(int address) {

        double rsl;

        if (pressureGaugeModel.read().equals("i2c")) {
            rsl = 1 * address;
        } else if (pressureGaugeModel.read().equals("rs232")) {
            rsl = 2 * address;
        } else {
            rsl = -1;
        }
        return rsl;
    }
}
