package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.IsoDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {

    @Test
    public void whenOldGenerated(@TempDir Path tempDir) throws IOException, JAXBException {
        File target = tempDir.resolve("target.xml").toFile();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 530.2);
        Employee worker3 = new Employee("Kolya", now, now, 2.21);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        DateTimeParser<Calendar> parser = new IsoDateTimeParser();
        Report xmlReport = new XMLReport(store, target.getAbsolutePath());
        File generatedXMLFile = Paths.get(xmlReport.generate(em -> true)).toFile();
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(generatedXMLFile))) {
            in.lines().forEach(rsl::add);
        }
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<employee name=\"").append(worker1.getName()).append("\" salary=\"").append(worker1.getSalary()).append("\">").append(System.lineSeparator())
                .append("    <hired>").append(parser.parse(worker1.getHired())).append("</hired>").append(System.lineSeparator())
                .append("    <fired>").append(parser.parse(worker1.getFired())).append("</fired>").append(System.lineSeparator())
                .append("</employee>").append(System.lineSeparator()).append(System.lineSeparator())
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<employee name=\"").append(worker2.getName()).append("\" salary=\"").append(worker2.getSalary()).append("\">").append(System.lineSeparator())
                .append("    <hired>").append(parser.parse(worker2.getHired())).append("</hired>").append(System.lineSeparator())
                .append("    <fired>").append(parser.parse(worker2.getFired())).append("</fired>").append(System.lineSeparator())
                .append("</employee>").append(System.lineSeparator()).append(System.lineSeparator())
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<employee name=\"").append(worker3.getName()).append("\" salary=\"").append(worker3.getSalary()).append("\">").append(System.lineSeparator())
                .append("    <hired>").append(parser.parse(worker3.getHired())).append("</hired>").append(System.lineSeparator())
                .append("    <fired>").append(parser.parse(worker3.getFired())).append("</fired>").append(System.lineSeparator())
                .append("</employee>");
        assertThat(rsl.toString()).isEqualTo(expect.toString());
    }
}