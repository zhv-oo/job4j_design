package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Ad {
    private final String name;
    private final int number;
    private final Contact contact;
    private final boolean active;
    private final String[] group;


    public Ad(String name, Integer number, Contact contact, boolean active, String[] group) {
        this.name = name;
        this.number = number;
        this.contact = contact;
        this.active = active;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "number='" + number + '\''
                + "name='" + name + '\''
                + "contact='" + contact + '\''
                + "active='" + active + '\''
                + "group='" + Arrays.toString(group) + '\''
                + '}';
    }

    public static void main(String[] args) {
        final Ad ad = new Ad("Tv", 875, new Contact("+79007771111"), true,
                new String[]{"tv", "secondHand"});
        System.out.println(ad);
    }
}
