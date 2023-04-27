package ru.job4j.ood.dip.ex3;

public class User {

    /*
    private String name;
    private EmailService emailService;

    public User(String name) {
        this.name = name;
        this.emailService = new EmailService();
    }

    public void changeName(String newName) {
        name = newName;
        emailService.sendEmail("admin@example.com", "Name change notification", "The user " + name + " changed their name.");
    }

    Класс User нарушает принцип DIP, потому что он жестко связан с реализацией отправки электронной почты
    в методе changeName(). Кроме того, у него есть прямая зависимость от EmailService.
    Необходимо выделить абстракцию NotificationService

     */
    private String name;
    private NotificationService notificationService;

    public User(String name, NotificationService notificationService) {
        this.name = name;
        this.notificationService = notificationService;
    }

    public void changeName(String newName) {
        name = newName;
        notificationService.sendNotification(
                "admin@example.com",
                "Name change notification",
                "The user " + name + " changed their name.");
    }
}