package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        String path = "./";
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        if (args.length != 0 && args[0].equals("-d")) {
            path = args[1];
        }
        Files.walkFileTree(Path.of(path), duplicatesVisitor);
        duplicatesVisitor.getDouble().forEach(System.out::println);
    }
}