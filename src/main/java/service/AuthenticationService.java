package service;

import exception.AuthenticationException;
import model.User;

public interface AuthenticationService {
    User register(String login, String password);

    User login(String login, String password) throws AuthenticationException;
}
