package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            boolean startRec = false;
            StringJoiner sj = new StringJoiner("");
            for (String s = in.readLine(); s != null; s = in.readLine()) {
                String[] str = s.split("\\s");
                int status = Integer.parseInt(str[0]);
                if ((!startRec && (status == 400 || status == 500))
                        || (startRec && (status == 200 || status == 300))) {
                    sj.add(str[1]);
                    sj.add(";");
                    startRec = !startRec;
                    if (!startRec) {
                        sj.add(System.lineSeparator());
                    }
                }
            }
            String rsl = sj.toString();
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                out.print(rsl);
            } catch (IOException e) {
                e.printStackTrace();
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