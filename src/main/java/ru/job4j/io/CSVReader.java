package ru.job4j.io;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private final List<String> lineSource =
            new ArrayList<>(List.of("name", "age", "birthDate", "education", "children"));

    public void readCsv(Path pathIn, String delim, String out, String filter) {
        String[] strings = filter.split(",");
        List<Integer> indexColumn = new ArrayList<>();
        for (String string : strings) {
            indexColumn.add(lineSource.indexOf(string));
        }
        try (var scanner = new Scanner(pathIn.toFile()).useDelimiter(delim + "|, |\\n")) {
            if (out.equals("stdout")) {
                int i = 0;
                while (scanner.hasNext()) {
                    String tmp = scanner.next();
                    if (i == lineSource.size()) {
                        i = 0;
                        System.out.println();
                    }
                    if (indexColumn.contains(i)) {
                        System.out.print(tmp);
                        System.out.print(" ");
                    }
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        new CSVReader().readCsv(Path.of(argsName.get("path")),
                argsName.get("delimiter"), argsName.get("out"), argsName.get("filter"));
    }
}