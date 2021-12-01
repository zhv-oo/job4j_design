package ru.job4j.isp.menu;

public abstract class BaseMenu {
    private final String name;

    public BaseMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}