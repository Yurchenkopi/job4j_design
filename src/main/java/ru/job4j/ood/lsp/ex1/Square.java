package ru.job4j.ood.lsp.ex1;

public class Square extends Rectangle {

    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

    /*
    Изменение предусловий в подклассе
     */

    public static void main(String[] args) {
        Rectangle rectangle = new Square();
        rectangle.setHeight(5);
    }
}
