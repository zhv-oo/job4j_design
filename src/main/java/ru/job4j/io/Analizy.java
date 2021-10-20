package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)
                    ))) {
                int chk = 0;
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    String[] tmp = line.split(" ");
                    int status = Integer.parseInt(tmp[0]);
                    if (status >= 400 && chk == 0) {
                        out.write(tmp[1] + ";");
                    } else if (status < 400 && chk == 1) {
                        out.write(tmp[1] + ";");
                        out.write(System.lineSeparator());
                    }
                    chk = status >= 400 ? 1 : 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /**
         * Для теста в метод main добавить.
         *  Analizy.unavailable("./data/server.log", "./data/unavailable.csv");
         */
    }
}