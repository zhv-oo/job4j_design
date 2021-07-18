package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final List<String> answerList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.readFile();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String inLine = "";
        String chkPhrase = "";
        List<String> out = new ArrayList<>();
        while (!inLine.equals(OUT)) {
            inLine = scanner.nextLine();
            if (inLine.equals(STOP) && !chkPhrase.equals(STOP)) {
                chkPhrase = STOP;
                out.add(inLine);
            } else if (!inLine.equals(CONTINUE) && chkPhrase.equals(STOP)) {
                out.add(inLine);
            } else if (!inLine.equals(OUT)) {
                String rndAnswer = answerList.get((int) (Math.random() * answerList.size()));
                System.out.println(rndAnswer);
                out.add(inLine);
                out.add(rndAnswer);
                chkPhrase = "";
            }
        }
        this.writeFile(out);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logChat.txt", "./data/answer.txt");
        cc.run();
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers,  Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(answerList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(List<String> out) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            out.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}