package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.Arrays;

public class Computer {
    private final String cpu;
    private final int core;
    private final boolean server;
    private final Person buyer;
    private final String[] statuses;

    public Computer(String cpu, int core, boolean server, Person buyer, String... statuses) {
        this.cpu = cpu;
        this.core = core;
        this.server = server;
        this.buyer = buyer;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Computer{"
                + "cpu=" + cpu
                + ", core=" + core
                + ", server=" + server
                + ", Buyer=" + buyer
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Gson gson = new GsonBuilder().create();
        final String pcJson =
                "{"
                        + "\"cpu\":intel,"
                        + "\"core\":4,"
                        + "\"server\":false,"
                        + "\"buyer\":"
                        + "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Client\",\"New\"]"
                        + "},"
                        + "\"statuses\":"
                        + "[\"Sale\",\"30%\"]"
                        + "}";
        final Computer pcMod = gson.fromJson(pcJson, Computer.class);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cpu", pcMod.cpu);
        jsonObject.put("core", pcMod.core);
        jsonObject.put("server", pcMod.server);
        jsonObject.put("buyer", pcMod.buyer);
        jsonObject.put("statuses", pcMod.statuses);

        System.out.println(jsonObject.toString());
    }
}