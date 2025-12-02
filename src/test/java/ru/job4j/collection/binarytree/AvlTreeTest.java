package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {
    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void treeBalancedAfterAscendingInsertions() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i <= 7; i++) {
            tree.insert(i);
        }
        assertTreeIsValidAvl(tree);
    }

    @Test
    void treeBalancedAfterDescendingInsertions() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 7; i >= 1; i--) {
            tree.insert(i);
        }
        assertTreeIsValidAvl(tree);
    }

    @Test
    void leftRightCaseIsApplied() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(10);
        tree.insert(20); // вызовет LR-случай на корне
        List<Integer> preOrder = tree.inPreOrder();
        // Ожидаем такую структуру:
        //     20
        //   /   \
        //  10   30
        assertThat(preOrder).containsExactly(20, 10, 30);
        assertTreeIsValidAvl(tree);
    }

    @Test
    void rightLeftCaseIsApplied() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(30);
        tree.insert(20); // вызовет RL-случай на корне
        List<Integer> preOrder = tree.inPreOrder();
        // Ожидаем:
        //     20
        //   /   \
        //  10   30
        assertThat(preOrder).containsExactly(20, 10, 30);
        assertTreeIsValidAvl(tree);
    }

    @Test
    void leftRotationHappensOnRightRightCase() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        List<Integer> preOrder = tree.inPreOrder();
        assertThat(preOrder).containsExactly(20, 10, 30);
        assertTreeIsValidAvl(tree);
    }

    @Test
    void rightRotationHappensOnLeftLeftCase() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10); // Right Rotation
        List<Integer> preOrder = tree.inPreOrder();
        // Ожидаем:
        //    20
        //  /   \
        // 10   30
        assertThat(preOrder).containsExactly(20, 10, 30);
        assertTreeIsValidAvl(tree);
    }

    @Test
    void removeLeafKeepsTreeBalanced() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i <= 7; i++) {
            tree.insert(i);
        }
        tree.remove(7); // лист
        assertThat(tree.contains(7)).isFalse();
        assertTreeIsValidAvl(tree);
    }

    @Test
    void removeNodeWithSingleChild() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(2); // узел 5 имеет одного ребёнка 2
        tree.remove(5);
        assertThat(tree.contains(5)).isFalse();
        assertThat(tree.inSymmetricalOrder()).containsExactly(2, 10);
        assertTreeIsValidAvl(tree);
    }

    @Test
    void removeNodeWithTwoChildren() {
        AvlTree<Integer> tree = new AvlTree<>();
        // Сбалансированное дерево
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(35);
        tree.remove(20); // корень с двумя детьми
        // in-order порядок должен остаться отсортированным
        assertThat(tree.inSymmetricalOrder()).containsExactly(5, 10, 15, 25, 30, 35);
        assertTreeIsValidAvl(tree);
    }


    static class SimpleNode<T extends Comparable<T>> {
        T key;
        SimpleNode<T> left;
        SimpleNode<T> right;
        SimpleNode(T key) {
            this.key = key;
        }
    }

    private <T extends Comparable<T>> void assertTreeIsValidAvl(AvlTree<T> tree) {
        List<T> inOrder = tree.inSymmetricalOrder();
        List<T> preOrder = tree.inPreOrder();
        // 1. In-order для BST обязан быть отсортирован
        List<T> sorted = new ArrayList<>(inOrder);
        sorted.sort(Comparable::compareTo);
        assertThat(inOrder).isEqualTo(sorted);
        // 2. Восстанавливаем структуру "логического дерева" из preOrder + inOrder
        SimpleNode<T> root = buildTreeFromPreIn(preOrder, inOrder);
        // 3. Проверяем, что это действительно BST и что оно сбалансировано как AVL
        assertIsBst(root, null, null);
        assertIsBalancedAvl(root);
    }

    private <T extends Comparable<T>> SimpleNode<T> buildTreeFromPreIn(
            List<T> preOrder, List<T> inOrder
    ) {
        if (preOrder.isEmpty() || inOrder.isEmpty()) {
            return null;
        }
        return buildTreeFromPreIn(
                preOrder, 0, preOrder.size() - 1,
                inOrder, 0, inOrder.size() - 1
        );
    }
    private <T extends Comparable<T>> SimpleNode<T> buildTreeFromPreIn(
            List<T> preOrder, int preStart, int preEnd,
            List<T> inOrder, int inStart, int inEnd
    ) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        T rootVal = preOrder.get(preStart);
        SimpleNode<T> root = new SimpleNode<>(rootVal);
        // Ищем позицию корня в in-order
        int rootIndexInOrder = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (Objects.equals(inOrder.get(i), rootVal)) {
                rootIndexInOrder = i;
                break;
            }
        }
        if (rootIndexInOrder == -1) {
            throw new IllegalStateException("Traversal sequences are inconsistent");
        }
        int leftSize = rootIndexInOrder - inStart;
        root.left = buildTreeFromPreIn(
                preOrder,
                preStart + 1,
                preStart + leftSize,
                inOrder,
                inStart,
                rootIndexInOrder - 1
        );
        root.right = buildTreeFromPreIn(
                preOrder,
                preStart + leftSize + 1,
                preEnd,
                inOrder,
                rootIndexInOrder + 1,
                inEnd
        );
        return root;
    }

    private <T extends Comparable<T>> void assertIsBst(
            SimpleNode<T> node, T min, T max
    ) {
        if (node == null) {
            return;
        }
        if (min != null) {
            assertThat(node.key).isGreaterThan(min);
        }
        if (max != null) {
            assertThat(node.key).isLessThan(max);
        }
        assertIsBst(node.left, min, node.key);
        assertIsBst(node.right, node.key, max);
    }

    private <T extends Comparable<T>> int assertIsBalancedAvl(SimpleNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = assertIsBalancedAvl(node.left);
        int rightHeight = assertIsBalancedAvl(node.right);
        int diff = Math.abs(leftHeight - rightHeight);
        assertThat(diff)
                .withFailMessage("AVL balance violated at node %s: left=%d, right=%d",
                        node.key, leftHeight, rightHeight)
                .isLessThanOrEqualTo(1);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}