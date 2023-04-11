package ru.job4j.ood.lsp.prod;

public class Shop extends AbstractStore<Food> {
    private String category;

    public Shop(String name, String category) {
        super(name);
        this.category = category;
    }
}
