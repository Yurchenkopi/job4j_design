package ru.job4j.collection.list;

public class Node<E> {
    private E item;
    private Node<E> prev;
    private Node<E> next;

    public Node(Node<E> prev, E item, Node<E> next) {
        this.prev = prev;
        this.item = item;
        this.next = next;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Node{"
                + "item=" + item
                + ", prev=" + prev
                + ", next=" + next
                + '}';
    }
}
