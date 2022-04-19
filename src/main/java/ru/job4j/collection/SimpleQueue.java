package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        T rsl;
        int index = size - 1;
        while (index != 0) {
            out.push(in.pop());
            index--;
        }
        rsl = in.pop();
        size--;
        while (index != size) {
            in.push(out.pop());
            index++;
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}