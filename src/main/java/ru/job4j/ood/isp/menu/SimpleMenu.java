package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        int i = 0;
        for (MenuItem item : rootElements) {
            if (parentName.equals(item.getName())) {
                List<MenuItem> childMenu = item.getChildren();
                rootElements.add(new MenuItemInfo(item.getName(), item.getChildren().stream().map(MenuItem::getName).collect(Collectors.toList()), actionDelegate, Integer.toString(i + 1)))
            }
            i++;
        }
        rootElements.add(new Menu.MenuItemInfo(parentName, childName))
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return Optional.empty();
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public MenuItemInfo next() {
                return null;
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        return Optional.empty();
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}