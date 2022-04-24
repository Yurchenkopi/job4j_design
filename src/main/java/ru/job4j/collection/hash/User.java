package ru.job4j.collection.hash;

import java.util.Objects;

public class User {
    private String name;
    private int children;
    private MyCalendar birthday;

    public User(String name, int children, MyCalendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return (int) getName().charAt(0) + birthday.getBirthday();
    }

}
