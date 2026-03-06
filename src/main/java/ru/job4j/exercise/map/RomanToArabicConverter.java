package ru.job4j.exercise.map;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RomanToArabicConverter {
    private final Map<Character, Integer> romanToArabicMap = new HashMap<>();

    public RomanToArabicConverter() {
        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);
        romanToArabicMap.put('L', 50);
        romanToArabicMap.put('C', 100);
        romanToArabicMap.put('D', 500);
        romanToArabicMap.put('M', 1000);
    }

    public int convert(String roman) {
        int rsl = 0;
        Pattern pattern = Pattern.compile("^([IVXCM])\\1{3,}$");
        if (roman.isEmpty() || pattern.matcher(roman).find()) {
            throw new IllegalArgumentException("Empty roman");
        }
        for (int i = 0; i < roman.length(); i++) {
            int current = romanToArabicMap.get(roman.charAt(i));
            if (((i + 1 < roman.length()) && (romanToArabicMap.get(roman.charAt(i + 1)) <= current))
                    || (roman.length() == 1)) {
                rsl += current;
            } else {
                rsl += romanToArabicMap.get(roman.charAt(i + 1)) - current;
                i++;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        var romanToArabicConverter = new RomanToArabicConverter();
        System.out.println(romanToArabicConverter.convert("MCMXCIV"));
    }
}
