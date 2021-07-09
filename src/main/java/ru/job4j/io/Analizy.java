package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {
    static public void unavailable(String source, String target) {
        List<String> tmpList = new ArrayList<>();
        try (BufferedReader line = new BufferedReader(new FileReader(source))) {
            tmpList = line.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            int chk = 0;
            for (String str : tmpList) {
               String[] line = str.split(" ");
               int status = Integer.parseInt(line[0]);
               if (status >= 400 && chk == 0) {
                   out.write(line[1] + ";");
               } else if (status < 400 && chk == 1) {
                   out.write(line[1] + ";");
                   out.write(System.lineSeparator());
               }
                chk = status >= 400 ? 1 : 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/server.log", "./data/unavailable.csv");
    }
}