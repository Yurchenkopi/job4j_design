package ru.job4j.ood.dip.ex2;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterSaver implements FileSaver {
    public void save(String fileName, String content) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(content);
        writer.close();
    }
}
