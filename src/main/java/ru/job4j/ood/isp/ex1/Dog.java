package ru.job4j.ood.isp.ex1;

public class Dog implements AnimalInterface {
    public void eat() {
    }

    public void drink() {
    }

    public void sleep() {
    }

    public void run() {
    }

    /**
     * Собаки не умеют летать, поэтому этот метод не может быть реализован
     * Это является нарушением принципа ISP
     * Для того, чтобы этого избежать, необходимо выделить отдельные интерфейсы для разных видов животных.
     * Например, класс Dog должен имплементировать интерфейс TerrestrialAnimalInterface(), в котором не будет метода fly()
     */

    public void fly() {
        throw new UnsupportedOperationException();
    }
}
