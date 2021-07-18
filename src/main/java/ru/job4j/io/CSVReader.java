package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private List<String> lineSource = new ArrayList<>();
    private List<Integer> indexColumn = new ArrayList<>();
    private final Path outPath = Path.of("./data/csvOut.txt");
    private final List<String> outList = new ArrayList<>();

    public void readCsv(Path pathIn, String delim, String out, String filter) {
        try (var scanner = new Scanner(pathIn.toFile()).useDelimiter(delim)) {
            if (scanner.hasNext()) {
                this.lineSource = List.of(filter.split(","));
                String[] tmps = scanner.nextLine().split(delim);
                int j = 0;
                for (String string : tmps) {
                    if (lineSource.contains(string)) {
                        this.indexColumn.add(j);
                    }
                    j++;
                }
                int i = 0;
                while (scanner.hasNext()) {
                    String[] tmp = scanner.nextLine().split(delim);
                    for (String s : tmp) {
                        if (i == tmp.length) {
                            i = 0;
                            outList.add(System.lineSeparator());
                        }
                        if (indexColumn.contains(i)) {
                            outList.add(s);
                            outList.add(" ");
                        }
                        i++;
                    }
                }
            }
            if (out.equals("stdout")) {
                outList.forEach(System.out::print);
            } else {
                writeFile(outList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(List<String> out) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(outPath.toFile(), Charset.forName("WINDOWS-1251"), true))) {
            out.forEach(pw::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        new CSVReader().readCsv(Path.of(argsName.get("path")),
                argsName.get("delimiter"), argsName.get("out"), argsName.get("filter"));
    }
}