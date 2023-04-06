package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.function.Predicate;

public class DevReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String targetFileName;

    public DevReport(Store store, DateTimeParser<Calendar> dateTimeParser, String targetFileName) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.targetFileName = targetFileName;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        try {
            Files.write(Paths.get(targetFileName), text.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFileName;
    }
}
