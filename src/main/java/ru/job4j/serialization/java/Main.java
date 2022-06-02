package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
        final Auto vaz2101 = new Auto(12, "A777AA777", false, new CarModel(1.6f, 77, "sedan"), new String[]{"7733 556644", "7744 332211"});

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
                        + "\"acceptedDriverLic\":[\"7733 556644\", \"7744 332211\"]"
                        + "}";

        final Auto vaz2101Mod = gson.fromJson(vaz2101Json, Auto.class);
        System.out.println(vaz2101Mod);

        JAXBContext context = JAXBContext.newInstance(Auto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(vaz2101, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Auto result = (Auto) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}