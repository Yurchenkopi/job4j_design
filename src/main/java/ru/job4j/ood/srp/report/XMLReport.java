package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private final Store store;
    private final String targetFileName;
    JAXBContext context = JAXBContext.newInstance(Employee.class);

    Marshaller marshaller = context.createMarshaller();

    public XMLReport(Store store, String targetFileName) throws JAXBException {
        this.store = store;
        this.targetFileName = targetFileName;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringJoiner xml = new StringJoiner(System.lineSeparator());
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            for (Employee employee : store.findBy(filter)) {
                try (StringWriter writer = new StringWriter()) {
                    marshaller.marshal(employee, writer);
                    xml.add(writer.getBuffer().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            Files.write(Paths.get(targetFileName), xml.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFileName;
    }
}
