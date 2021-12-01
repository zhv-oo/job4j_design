package ru.job4j.isp.menu;

class MyMenu extends BaseMenu {
    public MyMenu(String name) {
        super(name);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        BaseMenu menu1 = new MyMenu("Задача 1.");
        BaseMenu menu2 = new MyMenu("---- Задача 1.1.");
        BaseMenu menu3 = new MyMenu("--------- Задача 1.1.1.");
        BaseMenu menu4 = new MyMenu("--------- Задача 1.1.2.");
        BaseMenu menu5 = new MyMenu("----- Задача 1.2.");
        menu.addMenu("Задача 1.", menu1);
        menu.addMenu("Задача 1.1", menu2);
        menu.addMenu("---- Задача 1.1.", menu3);
        menu.addMenu("---- Задача 1.1.", menu4);
        menu.addMenu("Задача 1.2", menu5);
        menu.showMenu();
    }
}