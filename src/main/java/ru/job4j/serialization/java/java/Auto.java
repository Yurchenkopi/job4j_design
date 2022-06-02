package ru.job4j.serialization.java.java;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "auto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Auto {

    @XmlAttribute
    private int numsOfAccidents;

    @XmlAttribute
    private String regNum;

    @XmlAttribute
    private boolean regLimits;

    @XmlElement(name = "carmodel")
    private CarModel model;

    @XmlElementWrapper(name = "acceptedDriverLicenses")
    @XmlElement(name = "acceptedDriverLicense")
    private String[] acceptedDriverLic;

    public Auto() {

    }

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
