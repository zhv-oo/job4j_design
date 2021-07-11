package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                    .filter(obj -> !obj.startsWith("#") && !obj.isEmpty())
                    .map(obj -> obj.split("="))
                    .filter(obj -> {
                        if (obj.length != 2) {
                            throw new IllegalArgumentException();
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(p -> p[0], p -> p[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
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