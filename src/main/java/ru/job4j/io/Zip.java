package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getAbsolutePath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
         String directory = argsName.get("d");
         String exclude = argsName.get("e");
         String output = argsName.get("o");
        List<Path> fileToZip = Search.search(Path.of(directory),
                            obj -> !obj.toFile().getName().endsWith(exclude));
        new Zip().packFiles(fileToZip, Path.of(output));
    }
}