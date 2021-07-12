package ru.job4j.io.duplicates;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> filesMap = new HashMap<>();
    private List<Path> listDouble = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty prFile = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!filesMap.containsKey(prFile)) {
            filesMap.put(prFile, file.toAbsolutePath());
        } else {
            listDouble.add(filesMap.get(prFile));
            listDouble.add(file.toAbsolutePath());
        }

        return super.visitFile(file, attrs);
    }

    public List<Path> getDouble() {
        return listDouble;
    }
}