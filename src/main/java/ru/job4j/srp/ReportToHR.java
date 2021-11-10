package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * \* User: zhv
 * \* Date: 09.11.2021
 * \* Time: 19:42
 * \* Description: Реализация класса формирования отчета для отдела HR.
 * \
 */
public class ReportToHR implements Report {

    private Store store;

    public ReportToHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        List<Employee> sortedEmp = store.findBy(filter).stream()
                .sorted((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()))
                .collect(Collectors.toList());
        for (Employee employee : sortedEmp)  {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }

        return text.toString();
    }
}
