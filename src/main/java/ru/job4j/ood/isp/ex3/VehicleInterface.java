package ru.job4j.ood.isp.ex3;

/**
 * Этот интерфейс объединяет методы, относящиеся к управлению разными типами транспортных средств
 * (автомобилями, грузовиками, мотоциклами и т.д.). Однако, не все транспортные средства поддерживают все эти методы.
 * Например, мотоциклы не могут двигаться задним ходом и поворачивать налево/направо при движении задним ходом.
 * Это является нарушением ISP
 */

public interface VehicleInterface {
    public void start();
    public void stop();
    public void drive();
    public void reverse();
    public void turnLeft();
    public void turnRight();
}
