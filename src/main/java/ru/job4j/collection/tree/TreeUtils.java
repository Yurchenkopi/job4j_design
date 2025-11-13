package ru.job4j.collection.tree;

import ru.job4j.collection.Queue;
import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("root is null");
        }
        AtomicInteger count = new AtomicInteger(0);
        forEachBFS(root, node -> count.incrementAndGet());
        return count.get();
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("root is null");
        }
        Collection<T> collection = new ArrayList<>();
        forEachBFS(root, node -> collection.add(node.getValue()));
        return collection;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        boolean rsl = false;
        if (root == null) {
            throw new IllegalArgumentException("root is null");
        }
        var parentNode = findByKey(root, parent);
        if (parentNode.isPresent()) {
            rsl = parentNode.get().getChildren().add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("root is null");
        }
        return findDFS(root, node -> key.equals(node.getValue()));
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("root is null");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        if (key.equals(root.getValue())) {
            return Optional.of(root);
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            List<Node<T>> listOfChildren = stack.pop().getChildren();
            for (int i = 0; i < listOfChildren.size(); i++) {
                var child = listOfChildren.get(i);
                stack.push(child);
                if (key.equals(child.getValue())) {
                    listOfChildren.remove(i);
                    return Optional.of(child);
                }
            }
        }
        return Optional.empty();
    }

    private void forEachBFS(Node<T> root, Consumer<Node<T>> action) {
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            action.accept(node);
            for (Node<T> n : node.getChildren()) {
                queue.push(n);
            }
        }
    }

    private Optional<Node<T>> findDFS(Node<T> root, Predicate<Node<T>> condition) {
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (condition.test(node)) {
                return Optional.of(node);
            }
            for (Node<T> n : node.getChildren()) {
                stack.push(n);
            }
        }
        return Optional.empty();
    }
}
