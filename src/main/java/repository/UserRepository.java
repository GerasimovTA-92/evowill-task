package repository;

import model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findFirstByLogin(String login);

    User save(User user);

    void delete(Long id);
}
