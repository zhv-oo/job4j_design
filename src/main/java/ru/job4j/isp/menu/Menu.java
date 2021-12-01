package ru.job4j.isp.menu;

import java.util.*;

public class Menu implements ActionMenu {
    Map<String, List<BaseMenu>> menu = new LinkedHashMap<>();
    @Override
    public boolean addMenu(String pos, BaseMenu menuLine) {
        boolean res = false;
        List<BaseMenu> menus;
        if (menu.get(pos) == null) {
             menus = new ArrayList<>();
        } else {
            menus = menu.get(pos);
        }
        if (menuLine != null) {
            menus.add(menuLine);
            menu.put(pos, menus);
            res = true;
        }
        return res;
    }

    @Override
    public BaseMenu selectMenu(String pos) {
        BaseMenu res = null;
        for (BaseMenu menus : menu.get(pos)) {
            if (menus.getName().equals(pos)) {
                res = menus;
                break;
            }
        }
        return res;
    }

    void showMenu() {
        for (Map.Entry<String, List<BaseMenu>> pair : menu.entrySet()) {
            List<BaseMenu> value = pair.getValue();
            value.forEach(System.out::println);
        }
    }

}
