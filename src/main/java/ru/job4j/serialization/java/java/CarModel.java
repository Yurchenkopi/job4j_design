package ru.job4j.serialization.java.java;

public class CarModel {
    private final float engineVolume;
    private final int power;
    private final String bodywork;

    public CarModel(float engineVolume, int power, String bodywork) {
        this.engineVolume = engineVolume;
        this.power = power;
        this.bodywork = bodywork;
    }

    @Override
    public String toString() {
        return "CarModel{"
                + "engineVolume='" + engineVolume + '\''
                + ", power=" + power
                + ", bodywork='" + bodywork + '\''
                + '}';
    }
}
