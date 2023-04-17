package ru.job4j.ood.isp.ex2;

public class CreditCardPayment implements PaymentInterface {
    public void processPayment() {
    }

    public void refundPayment() {
    }

    /**
     * Класс не использует методы cancelPayment(), verifyPayment(), а следовательно
     * чтобы не нарушался принцип ISP их следует выделить в отдельный интерфейс
     */

    public void cancelPayment() {
        throw new UnsupportedOperationException();
    }


    public void verifyPayment() {
        throw new UnsupportedOperationException();
    }
}
