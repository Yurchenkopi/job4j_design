package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Store store;
    private final String targetFileName;
    private final Gson exportToJson;

    public JSONReport(Store store, String targetFileName, Gson exportToJson) {
        this.store = store;
        this.targetFileName = targetFileName;
        this.exportToJson = exportToJson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringJoiner text = new StringJoiner(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.add(exportToJson.toJson(employee));
        }
        try {
            Files.write(Paths.get(targetFileName), text.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFileName;
    }
}
