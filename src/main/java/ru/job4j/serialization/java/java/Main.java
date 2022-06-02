package ru.job4j.serialization.java.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Auto vaz2101 = new Auto(12, "A777AA777", false, new CarModel(1.6f, 77, "sedan"), new String[] {"7733 556644", "7744 332211"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(vaz2101));

        final String vaz2101Json =
                "{"
                        + "\"numsOfAccidents\":12,"
                        + "\"regNum\":\"A777AA777\","
                        + "\"regLimits\":false,"
                        + "\"model\":"
                                + "{"
                                + "\"engineVolume\":1.6,"
                                + "\"power\":77,"
                                + "\"bodywork\":\"sedan\""
                        + "},"
                        + "\"acceptedDriversLis\":[\"7733 556644\", \"7744 332211\"]"
                + "}";

        final Auto vaz2101Mod = gson.fromJson(vaz2101Json, Auto.class);
        System.out.println(vaz2101Mod);
    }
}
