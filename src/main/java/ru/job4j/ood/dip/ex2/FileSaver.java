package ru.job4j.ood.dip.ex2;

import java.io.IOException;

public interface FileSaver {
    void save(String fileName, String content) throws IOException;
}
