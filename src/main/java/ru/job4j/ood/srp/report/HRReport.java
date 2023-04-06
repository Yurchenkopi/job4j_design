package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.function.Predicate;

public class HRReport implements Report {

    private final Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)
                .stream()
                .sorted(new EmployeeSalaryDesc())
                .toList()) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private static class EmployeeSalaryDesc implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return Double.compare(o2.getSalary(), o1.getSalary());
        }
    }
}

