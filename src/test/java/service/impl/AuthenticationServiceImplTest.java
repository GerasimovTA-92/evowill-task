package service.impl;

import exception.AuthenticationException;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.AuthenticationService;
import service.UserService;
import util.SessionFactoryUtil;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceImplTest {
    private static AuthenticationService authenticationService;
    private static UserService userService;
    private static UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        userRepository = new UserRepositoryImpl(SessionFactoryUtil.getSessionFactory(), User.class);
        userService = new UserServiceImpl(userRepository);
        authenticationService = new AuthenticationServiceImpl(userService);
    }

    @Test
    public void register_correctData_Ok() {
        assertEquals("testLogin",
                authenticationService.register("testLogin", "testPassword").getLogin());
    }

    @Test
    public void login_correctData_Ok() throws AuthenticationException {
        User user = new User();
        user.setLogin("test2");
        user.setPassword("12345");
        userRepository.save(user);
        assertEquals(user, authenticationService.login(user.getLogin(), user.getPassword()));
    }

    @Test
    public void login_invalidData_Ok() {
        User user = new User();
        user.setLogin("test3");
        user.setPassword("12345");
        userRepository.save(user);
        assertThrows(AuthenticationException.class, () -> authenticationService.login("invalid", user.getPassword()));
    }
}