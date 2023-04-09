package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class JSONReportTest  {

    @Test
    public void whenOldGenerated(@TempDir Path tempDir) throws IOException {
        File target = tempDir.resolve("target.json").toFile();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 530.2);
        Employee worker3 = new Employee("Kolya", now, now, 2.21);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report jsonReport = new JSONReport(store, target.getAbsolutePath(), new GsonBuilder().create());
        File generatedJSONFile = Paths.get(jsonReport.generate(em -> true)).toFile();
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(generatedJSONFile))) {
            in.lines().forEach(rsl::add);
        }
        StringBuilder expect = new StringBuilder()
                .append("{\"name\":\"").append(worker1.getName()).append("\",\"hired\":{\"year\":")
                .append(worker1.getHired().get(Calendar.YEAR)).append(",\"month\":")
                .append(worker1.getHired().get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(worker1.getHired().get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(worker1.getHired().get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(worker1.getHired().get(Calendar.MINUTE)).append(",\"second\":")
                .append(worker1.getHired().get(Calendar.SECOND)).append("},\"fired\":{\"year\":")
                .append(worker1.getFired().get(Calendar.YEAR)).append(",\"month\":")
                .append(worker1.getFired().get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(worker1.getFired().get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(worker1.getFired().get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(worker1.getFired().get(Calendar.MINUTE)).append(",\"second\":")
                .append(worker1.getFired().get(Calendar.SECOND)).append("},\"salary\":")
                .append(worker1.getSalary()).append("}").append(System.lineSeparator())
                .append("{\"name\":\"").append(worker2.getName()).append("\",\"hired\":{\"year\":")
                .append(worker2.getHired().get(Calendar.YEAR)).append(",\"month\":")
                .append(worker2.getHired().get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(worker2.getHired().get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(worker2.getHired().get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(worker2.getHired().get(Calendar.MINUTE)).append(",\"second\":")
                .append(worker2.getHired().get(Calendar.SECOND)).append("},\"fired\":{\"year\":")
                .append(worker2.getFired().get(Calendar.YEAR)).append(",\"month\":")
                .append(worker2.getFired().get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(worker2.getFired().get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(worker2.getFired().get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(worker2.getFired().get(Calendar.MINUTE)).append(",\"second\":")
                .append(worker2.getFired().get(Calendar.SECOND)).append("},\"salary\":")
                .append(worker2.getSalary()).append("}").append(System.lineSeparator())
                .append("{\"name\":\"").append(worker3.getName()).append("\",\"hired\":{\"year\":")
                .append(worker3.getHired().get(Calendar.YEAR)).append(",\"month\":")
                .append(worker3.getHired().get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(worker3.getHired().get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(worker3.getHired().get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(worker3.getHired().get(Calendar.MINUTE)).append(",\"second\":")
                .append(worker3.getHired().get(Calendar.SECOND)).append("},\"fired\":{\"year\":")
                .append(worker3.getFired().get(Calendar.YEAR)).append(",\"month\":")
                .append(worker3.getFired().get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(worker3.getFired().get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(worker3.getFired().get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(worker3.getFired().get(Calendar.MINUTE)).append(",\"second\":")
                .append(worker3.getFired().get(Calendar.SECOND)).append("},\"salary\":")
                .append(worker3.getSalary()).append("}");
        assertThat(rsl.toString()).isEqualTo(expect.toString());
    }
}