package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        Random random = new Random();
        boolean stop = false;
        Scanner sc = new Scanner(System.in);
        String sl = System.lineSeparator();
        System.out.println("Начните чат." + sl
                + "Введите вопрос или:" + sl
                + "\"стоп\" - прервать работу программы;" + sl
                + "\"продолжить\" - возобновить работу программы;" + sl
                + "\"закончить\" - завершить работу программы;" + sl);
        String question = sc.nextLine();
        formatAdd(log, question, "Q");
        while (!question.equals(OUT)) {
            if (question.equals(STOP) && !stop) {
                stop = true;
            } else if (question.equals(CONTINUE) && stop) {
                stop = false;
            }
            if (!stop) {
                String answer = answers.get(Math.abs(random.nextInt()) % (answers.size() - 1));
                formatAdd(log, answer, "A");
                System.out.println(answer);
            }
            question = sc.nextLine();
            formatAdd(log, question, "Q");
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> bufList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            bufList = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8), true)) {
            for (String s : log) {
                out.printf("%s" + System.lineSeparator(), s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formatAdd(List<String> list, String str, String prefix) {
        StringBuilder sb = new StringBuilder();
        LocalDateTime sent = LocalDateTime.now();
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        sb.append(sent.format(FORMATTER)).append("\s").append(prefix).append(": ").append(str);
        list.add(sb.toString());
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/ConsoleChat/log.txt", "./data/ConsoleChat/botAnswers.txt");
        cc.run();
    }
}