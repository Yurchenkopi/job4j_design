package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

public class DevReportTest {

    @Test
    public void whenOldGenerated(@TempDir Path tempDir) throws IOException {
        File target = tempDir.resolve("target.csv").toFile();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 530.2);
        Employee worker3 = new Employee("Vasya", now, now, 51.2);
        Employee worker4 = new Employee("Kolya", now, now, 2.21);
        Employee worker5 = new Employee("Ann", now, now, 1000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        store.add(worker5);
        Report devReport = new DevReport(store, parser, target.getAbsolutePath());
        File generatedCsvFile = Paths.get(devReport.generate(em -> true)).toFile();
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(generatedCsvFile))) {
            in.lines().forEach(rsl::add);
        }
        StringBuilder expect = new StringBuilder()
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(parser.parse(worker1.getHired())).append(";")
                .append(parser.parse(worker1.getFired())).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(parser.parse(worker2.getHired())).append(";")
                .append(parser.parse(worker2.getFired())).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(parser.parse(worker3.getHired())).append(";")
                .append(parser.parse(worker3.getFired())).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker4.getName()).append(";")
                .append(parser.parse(worker4.getHired())).append(";")
                .append(parser.parse(worker4.getFired())).append(";")
                .append(worker4.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker5.getName()).append(";")
                .append(parser.parse(worker5.getHired())).append(";")
                .append(parser.parse(worker5.getFired())).append(";")
                .append(worker5.getSalary()).append(";");
        assertThat(rsl.toString()).isEqualTo(expect.toString());
    }
}