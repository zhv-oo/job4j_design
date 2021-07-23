package ru.job4j.finder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.UsageLog4j;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.job4j.finder.Search.search;

public class Finder {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    private final String outPath;
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

    public Finder(String outPath) {
        this.outPath = outPath;
    }

    private void writeFile() {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(outPath, StandardCharsets.UTF_8, true))) {
            resultList.forEach(pw::println);
        } catch (IOException e) {
            LOG.error("Exception:", e);
        }
    }

    private void searcher(String searchType, String startDir, String tag) throws IOException {
        if (searchType.equals("mask")) {
            resultList = search(Path.of(startDir),
                    p -> p.toFile().getName().endsWith(tag.replaceFirst("\\*", "")));
        } else if (searchType.equals("name")) {
            resultList = search(Path.of(startDir),
                    p -> p.toFile().getName().equals(tag));
        } else {
            resultList = search(Path.of(startDir),
                    p -> p.toFile().getName().contains(tag));
        }
    }

    public static void main(String[] args) {
        try {
            GetArgs getArgs = GetArgs.of(args);
            Finder finder = new Finder(getArgs.get("o"));
            finder.searcher(getArgs.get("t"), getArgs.get("d"), getArgs.get("n"));
            finder.writeFile();
        } catch (IllegalArgumentException | IOException e) {
            HL.forEach(System.out::println);
        }
    }
}