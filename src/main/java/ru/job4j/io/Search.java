package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;


public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar search.jar ROOT_FOLDER.");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Enter file extension, Usage java -jar search.jar ROOT_FOLDER FILE_EXTENSION.");
        }
        Path start = Paths.get(args[0]);
        String fileEx = args[1];
        search(start, p -> p.toFile().getName().endsWith(fileEx)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static class SearchFiles
            extends SimpleFileVisitor<Path> {
        private final Predicate<Path> condition;
        private List<Path> list = new ArrayList<>();

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        public List<Path> getPaths() {
            return list;
        }

        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attr) {
            if (condition.test(file)) {
                list.add(file);
            }
            return CONTINUE;
        }
    }
}