package ru.job4j.ood.dip.ex2;

import java.io.IOException;

public class FileManager {
    /*
    public void save(String fileName, String content) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(content);
        writer.close();
    }
    Класс FileManager зависит от конкретной реализации записи в файл через FileWriter
    Необходимо выделить абстракцию FileSaver
    FileWriterSaver - пример реализации FileSaver
    */

    private FileSaver fileSaver;

    public FileManager(FileSaver fileSaver) {
        this.fileSaver = fileSaver;
    }

    public void save(String fileName, String content) throws IOException {
        fileSaver.save(fileName, content);
    }
}