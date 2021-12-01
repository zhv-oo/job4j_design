package ru.job4j.isp.menu;

public interface ActionMenu {
    boolean addMenu(String pos, BaseMenu menu);
    BaseMenu selectMenu(String pos);
}
