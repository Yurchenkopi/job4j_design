package ru.job4j.serialization.java.java;

import java.util.Arrays;

public class Auto {
    private final int numsOfAccidents;
    private final String regNum;
    private final boolean regLimits;
    private final CarModel model;
    private final String[] acceptedDriverLic;

    public Auto(int numsOfAccidents, String regNum, boolean regLimits, CarModel model, String[] acceptedDriverLic) {
        this.numsOfAccidents = numsOfAccidents;
        this.regNum = regNum;
        this.regLimits = regLimits;
        this.model = model;
        this.acceptedDriverLic = acceptedDriverLic;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "numsOfAccidents=" + numsOfAccidents
                + ", regNum='" + regNum + '\''
                + ", regLimits=" + regLimits
                + ", model=" + model
                + ", acceptedDriversLis=" + Arrays.toString(acceptedDriverLic)
                + '}';
    }
}
