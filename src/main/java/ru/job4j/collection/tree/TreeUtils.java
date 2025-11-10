package ru.job4j.collection.tree;

import ru.job4j.collection.Queue;
import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        return ((Collection<T>) findAll(root)).size();
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        Collection<T> collection = new ArrayList<>();
        if (root != null) {
            Queue<Node<T>> queue = new SimpleQueue<>();
            collection.add(root.getValue());
            queue.push(root);
            List<Node<T>> listOfChildren = queue.poll().getChildren();
            while (!listOfChildren.isEmpty()) {
                for (Node<T> node : listOfChildren) {
                    queue.push(node);
                    collection.add(node.getValue());
                }
                listOfChildren = queue.poll().getChildren();
            }
        } else {
            throw new IllegalArgumentException("root is null");
        }
        return collection;
    }
}
