package ru.job4j.srp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

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
        List<Employee> tmp = new ArrayList<>();
        for (Employee employee : store.findBy(filter)) {
                tmp.add(employee);
        }
        tmp.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Double.compare(o2.getSalary(), o1.getSalary());
            }
        });

        for (Employee employee : tmp) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }

        return text.toString();
    }


}
