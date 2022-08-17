package repository.impl;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import repository.AbstractRepository;
import repository.UserRepository;
import java.util.Optional;

public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
    public UserRepositoryImpl(SessionFactory factory, Class<User> clazz) {
        super(factory, clazz);
    }

    @Override
    public Optional<User> findFirstByLogin(String login) {
        try (Session session = factory.openSession()) {
            Query<User> findByLogin = session.createQuery("from User u where u.login = :login", User.class);
            findByLogin.setParameter("login", login);
            return findByLogin.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("User not found", e);
        }
    }
}
