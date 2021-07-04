package ru.job4j.collection.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user = new User("Ivan", 2,
                new GregorianCalendar(1980, 11, 11, 10, 10, 10));
        User userTwo = new User("Ivan", 2,
                new GregorianCalendar(1980, 11, 11, 10, 10, 10));
        Map<User, Object> test = new HashMap<>();
        test.put(user, new Object());
        test.put(userTwo, new Object());
        for (User userT : test.keySet()) {
            System.out.println(userT);
            System.out.println(userT.hashCode());
        }
        System.out.println(test);
    }
}