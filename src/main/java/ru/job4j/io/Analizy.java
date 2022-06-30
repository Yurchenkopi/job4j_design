package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileWriter(target))) {
            boolean startRec = false;
            for (String s = in.readLine(); s != null; s = in.readLine()) {
                String[] str = s.split("\\s");
                int status = Integer.parseInt(str[0]);
                if ((!startRec && (status == 400 || status == 500))
                        || (startRec && (status == 200 || status == 300))) {
                    out.print(str[1]);
                    out.print(";");
                    startRec = !startRec;
                    if (!startRec) {
                        out.println();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analizy analiz = new Analizy();
        analiz.unavailable("server.log", "unavailable.csv");
    }
}