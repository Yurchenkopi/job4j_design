package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        Optional<ItemInfo> itInfo = findItem(parentName);
        boolean rsl = true;
        if (itInfo.isEmpty()) {
            rsl = false;
        } else {
            itInfo.get().menuItem.getChildren().add((MenuItem) new MenuItemInfo(
                    childName,
                    new ArrayList<>(),
                    actionDelegate,
                    ""));
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> itInfo = findItem(itemName);
        Optional<MenuItemInfo> rsl = Optional.empty();
        if (itInfo.isPresent()) {
            rsl = Optional.of(new MenuItemInfo(itInfo.get().menuItem, itInfo.get().number));
        } else {
            throw new NoSuchElementException();
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<ItemInfo> it = new DFSIterator();
        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                return new MenuItemInfo(it.next().menuItem, it.next().number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        Iterator<ItemInfo> it = new DFSIterator();
        while (it.hasNext()) {
            ItemInfo temp = it.next();
            if (name.equals(temp.menuItem.getName())) {
                rsl = Optional.of(temp);
                break;
            }
        }
        return rsl;
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