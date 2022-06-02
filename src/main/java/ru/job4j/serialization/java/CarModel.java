package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "carmodel")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarModel {

    @XmlAttribute
    private float engineVolume;

    @XmlAttribute
    private int power;

    @XmlAttribute
    private String bodywork;

    public CarModel() {

    }

    public CarModel(float engineVolume, int power, String bodywork) {
        this.engineVolume = engineVolume;
        this.power = power;
        this.bodywork = bodywork;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public int getPower() {
        return power;
    }

    public String getBodywork() {
        return bodywork;
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
