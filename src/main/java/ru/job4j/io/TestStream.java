package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.Arrays;
import java.util.Map;

public class TestStream {
    public static void main(String[] args) throws IOException {
        String s = "Hello";
        int i = 1000;
        File f = new File("C:\\projects\\job4j_design\\1.txt");
        f.createNewFile();
        Path p = Paths.get("C:\\projects\\job4j_design\\2.txt");
        Files.write(p, "Hello".getBytes(StandardCharsets.UTF_8));
        try (InputStream in = new BufferedInputStream(new FileInputStream(f));
        OutputStream out = new BufferedOutputStream(new FileOutputStream("newFile.bin"))) {


            int sym = in.read();
            while (sym > 0) {
                out.write(sym);
                sym = in.read();
            }
        }
   //     Files.move(f.toPath(), f.toPath().getParent().resolve("data\\2.txt"), StandardCopyOption.REPLACE_EXISTING);
        BasicFileAttributeView basicView = Files.getFileAttributeView(f.toPath(), BasicFileAttributeView.class);
        System.out.println(basicView.readAttributes().lastAccessTime().toMillis());
        System.out.println("--------------");
        for (Map.Entry<String, Object> map : Files.readAttributes(f.toPath(), "*").entrySet()) {
            System.out.println(map.getKey() + " : " + map.getValue());
        }
        System.out.println("Done! ");
    }
}
