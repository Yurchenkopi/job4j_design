package ru.job4j.cache;

import ru.job4j.io.Search;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws IOException {
        String[] args = new String[]{cachingDir, ".txt"};
        Search.validate(args);
        Path start = Paths.get(args[0]);
        Search.search(start, p -> p.toString().endsWith(args[1])).forEach(System.out::println);
        put(key, key);
        return null;
    }

}