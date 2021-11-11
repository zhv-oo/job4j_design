package ru.job4j.srp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

/**
 * \* User: zhv
 * \* Date: 11.11.2021
 * \* Time: 20:00
 * \* Description: Фармирование отчета в JSON
 * \
 */
public class ReportToJson implements Report {

    private Store store;

    public ReportToJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new GsonBuilder().create().toJson(store);
    }
}
