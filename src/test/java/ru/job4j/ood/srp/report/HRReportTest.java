package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class HRReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 530.2);
        Employee worker3 = new Employee("Vasya", now, now, 51.2);
        Employee worker4 = new Employee("Kolya", now, now, 2.21);
        Employee worker5 = new Employee("Ann", now, now, 1000);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        store.add(worker5);
        Report hrReport = new HRReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker5.getName()).append(" ")
                .append(worker5.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator())
                .append(worker4.getName()).append(" ")
                .append(worker4.getSalary())
                .append(System.lineSeparator());
        assertThat(hrReport.generate(em -> true)).isEqualTo(expect.toString());
    }
}