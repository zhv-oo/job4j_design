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
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            while (!inLine.equals(OUT)) {
                inLine = scanner.nextLine();
                if (inLine.equals(STOP) && !chkPhrase.equals(STOP)) {
                    chkPhrase = STOP;
                    pw.println(inLine);
                } else if (!inLine.equals(CONTINUE) && chkPhrase.equals(STOP)) {
                    pw.println(inLine);
                } else if (!inLine.equals(OUT)) {
                    String rndAnswer = answerList.get(this.getRandomAnswer());
                    System.out.println(rndAnswer);
                    pw.println(inLine);
                    pw.println(rndAnswer);
                    chkPhrase = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private int getRandomAnswer() {
        int rsl = 0;
        Random random = new Random();
        OptionalInt optRsl = random.ints(0, (answerList.size() - 1)).findFirst();
        if (optRsl.isPresent()) {
            rsl = optRsl.getAsInt();
        }
        return rsl;
    }
}