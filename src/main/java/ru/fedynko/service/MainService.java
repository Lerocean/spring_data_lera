package ru.fedynko.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.fedynko.entity.User;
import ru.fedynko.repository.UserRepository;

@Service
public class MainService {

    private static final Logger logger = LoggerFactory.getLogger(MainService.class.getName());

    private final UserRepository userRepository;

    public MainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void doTestOperations() {
        User lera = createUser(new User("Lera", "Fedynko", 18));
        logger.info("OPERATION create LERA!");
        getAllUsers().forEach(user -> logger.info(user.toString()));

        lera.setAge(19);
        updateUser(lera);
        logger.info("OPERATION update!");
        getAllUsers().forEach(user -> logger.info(user.toString()));

        User sasha = createUser(new User("Sasha", "Shumilin", 34));
        logger.info("OPERATION create SASHA!");
        getAllUsers().forEach(user -> logger.info(user.toString()));

        deleteUser(sasha);
        logger.info("OPERATION delete!");
        getAllUsers().forEach(user -> logger.info(user.toString()));


        User foundedByParameter = findByParameter(lera.getName());
        logger.info("OPERATION findByParameter!");
        logger.info("Founded user: {}", foundedByParameter);

        createUser(new User("qwe", "asd", 99));
        createUser(new User("qwe", "zxc", 88));
        createUser(new User("qwe", "fgf", 77));
        logger.info("CREATE 3 USER");
        userRepository.findAllByName("qwe").forEach(user -> logger.info(user.toString()));

    }

    private User findByParameter(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}