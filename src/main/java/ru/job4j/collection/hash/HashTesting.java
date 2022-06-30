package ru.job4j.collection.hash;

import ru.job4j.map.SimpleMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HashTesting {

    public static void main(String[] args) {
        User u1 = new User("Ivan", 1, new MyCalendar(5, 5, 1990));
        User u2 = new User("Ivan", 1, new MyCalendar(5, 5, 1990));
        Map<User, Object> map = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "Apple");
        map2.put(5, "Orange");
        map2.put(10, "Grape");
        map2.put(11, "Orange");
        map2.put(23, "Grape");
        map2.put(54, "Orange");
        map2.put(105, "Grape");
        String s = "=";
        String[] ar = s.split("=", 2);
        System.out.println(ar.length);
        for (Integer i : map2.keySet()) {
            System.out.println(i);
        }
        map.put(u1, new Object());
        map.put(u2, new Object());
        map.keySet()
                .forEach(k -> System.out.println("key " + "\n"
                        + "- hashcode: " + Integer.toHexString(k.hashCode())
                        + "       |"  + "\n"
                        + "- hash:     " + Integer.toHexString(hash(k))
                        + "       |      value:  "
                        + map.get(k) + " ; " + "\n"
                        + "----"  + "\n"

                ));
    }

    public static int hash(Object key) {
        int rsl = 0;
        if (key != null) {
            int h = key.hashCode();
            rsl = (h) ^ (h >>> 16);
        }
        return rsl;
    }
}
