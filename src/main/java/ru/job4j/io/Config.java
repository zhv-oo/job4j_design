package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();
    private String key = "";

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        if (key.contains("=")) {
            throw new IllegalArgumentException();
        }
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            this.values = read.lines()
                    .filter(obj -> !obj.startsWith("#") && obj.contains("="))
                    .map(obj -> obj.split("="))
                    .collect(Collectors.toMap(p -> p[0], p -> p[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        this.key = key;
        load();
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
           System.out.println(new Config("app.properties"));
    }
}