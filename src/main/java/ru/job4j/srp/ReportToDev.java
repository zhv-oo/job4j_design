package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * \* User: zhv
 * \* Date: 09.11.2021
 * \* Time: 19:38
 * \* Description: Реализация класса формирования отчета для отдела Dev.
 * \
 */
public class ReportToDev implements Report {

    private Store store;

    public ReportToDev(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append("<name>").append(employee.getName()).append("</name>")
                    .append(System.lineSeparator())
                    .append("<hired>").append(employee.getHired()).append("</hired>")
                    .append(System.lineSeparator())
                    .append("<fired>").append(employee.getFired()).append("</fired>")
                    .append(System.lineSeparator())
                    .append("<salary>").append(employee.getSalary()).append("</salary>")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
