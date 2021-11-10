package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * \* User: zhv
 * \* Date: 09.11.2021
 * \* Time: 19:36
 * \* Description: Реализация класса формирования отчета для отдела Buh.
 * \
 */
public class ReportToBuh implements Report {

    private Store store;
    private static final int DOLLAR_PER_ROUBLE = 72;

    public ReportToBuh(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / DOLLAR_PER_ROUBLE).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
