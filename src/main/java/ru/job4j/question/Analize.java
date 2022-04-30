package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        final Map<Integer, User> tempMap = previous.stream()
                .collect(Collectors.toMap(User::getId, v -> v));
        int changed = (int) current.stream()
                .filter(u -> tempMap.containsKey(u.getId()) && !tempMap.get(u.getId()).equals(u))
                .count();
        Set<User> tempList = new HashSet<>(previous);
        tempList.addAll(current);
        int added = tempList.size() - previous.size() - changed;
        tempList = new HashSet<>(previous);
        tempList.removeAll(current);
        int deleted = tempList.size() - changed;
        return new Info(added, changed, deleted);
    }

}