package ru.job4j.collection.hash;

import java.util.HashMap;
import java.util.Map;

public class HashTesting {

    public static void main(String[] args) {
        User u1 = new User("Ivan", 1, new MyCalendar(5, 5, 1990));
        User u2 = new User("Ivan", 1, new MyCalendar(5, 5, 1990));
        Map<User, Object> map = new HashMap<>();
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
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
