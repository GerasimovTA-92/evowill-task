package service;

import model.User;
import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByLogin(String login);
}
