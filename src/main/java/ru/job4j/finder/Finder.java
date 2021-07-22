package ru.job4j.finder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.Search;
import ru.job4j.io.UsageLog4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Finder {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    private String outPath;
    private String startDir;
    @SuppressWarnings("checkstyle:ConstantName")
    private static final List<String> HL = List.of(
            "Для запуска в консоли введите java -jar finder.jar -d=c:/ -n=*.txt -t=mask -o=log.txt",
            "Где ключи:",
            "-d - директория, в которой начинать поиск.",
            "-n - имя файла, маска, либо регулярное выражение.",
            "-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.",
            "-o - результат записать в файл."
             );
    private List<Path> resultList = new ArrayList<>();

    public Finder(String startDir, String outPath) {
        this.startDir = startDir;
        this.outPath = outPath;
    }

    public List<Path> getResultList() {
        return resultList;
    }

    private static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        Search.SearchFiles searcher = new Search.SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static class SearchFiles
            extends SimpleFileVisitor<Path> {
        private final Predicate<Path> condition;
        private final List<Path> list = new ArrayList<>();


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

    private void writeFile(List<Path> out) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(outPath, StandardCharsets.UTF_8, true))) {
            out.forEach(pw::println);
        } catch (IOException e) {
            LOG.error("Exception:", e);
        }
    }

    public static void main(String[] args) {
        try {
            GetArgs getArgs = GetArgs.of(args);
            Finder finder = new Finder(getArgs.get("d"), getArgs.get("o"));
            String typeSearch = getArgs.get("t");
            if (typeSearch.equals("mask")) {
                finder.resultList = search(Path.of(finder.startDir),
                        p -> p.toFile().getName().endsWith(getArgs.get("n").replaceFirst("\\*", "")));
            } else if (typeSearch.equals("name")) {
                finder.resultList = search(Path.of(finder.startDir),
                        p -> p.toFile().getName().startsWith(getArgs.get("n")));
            } else {
                finder.resultList = search(Path.of(finder.startDir),
                        p -> p.toFile().getName().contains(getArgs.get("n")));
            }
            finder.writeFile(finder.resultList);
        } catch (IllegalArgumentException | IOException e) {
            HL.forEach(System.out::println);
        }
    }
}