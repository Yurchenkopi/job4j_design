package ru.job4j.ood.lsp.prod;

public class Warehouse extends AbstractStore<Food>  {
    private int capacity;

    public Warehouse(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }
}
