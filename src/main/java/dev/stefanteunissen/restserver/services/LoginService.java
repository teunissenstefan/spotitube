package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.UserDAO;
import dev.stefanteunissen.restserver.exceptions.UserIncorrectDetailsEnteredException;
import dev.stefanteunissen.restserver.models.User;

import javax.inject.Inject;

public class LoginService {
    @Inject
    public UserDAO userDAO;

    /**
     * Log the user in
     *
     * @param username
     * @param enteredPassword
     * @return
     */
    public User login(String username, String enteredPassword) {
        User user = userDAO.getUserByUsername(username);

        if (user.passwordCheck(enteredPassword))
            return user;
        throw new UserIncorrectDetailsEnteredException("Incorrect login details");
    }
}
