package ru.job4j.ood.dip.ex1;

public class UserService {
/*
    private UserRepository userRepository = new MySqlUserRepository();

    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }

    Класс UserService зависит от конкретной реализации базы данных MySqlUserRepository.
    Необходимо использовать интерфейс UserRepository, чтобы класс UserService зависел от абстракции,
    а не от конкретной реализации
 */

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }
}
