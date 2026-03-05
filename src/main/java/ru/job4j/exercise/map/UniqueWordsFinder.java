package ru.job4j.exercise.map;

import java.util.*;

public class UniqueWordsFinder {
    public List<String> findUniqueWords(List<String> words) {
        List<String> uniqueWords = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String word : words) {
            map.computeIfPresent(word, (k, v) -> v + 1);
            map.putIfAbsent(word, 1);
        }
        for (String word : map.keySet()) {
            if (map.get(word) == 1) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "grape");
        var uniqueWordsFinder = new UniqueWordsFinder();
        System.out.println(uniqueWordsFinder.findUniqueWords(words));
    }
}
