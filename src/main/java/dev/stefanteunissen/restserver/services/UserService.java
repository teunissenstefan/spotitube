package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.TokenDAO;
import dev.stefanteunissen.restserver.data.UserDAO;
import dev.stefanteunissen.restserver.models.User;

import javax.inject.Inject;
import java.util.ArrayList;

public class UserService {
    @Inject
    public UserDAO userDAO;
    @Inject
    public TokenDAO tokenDAO;

    /**
     * Get all users
     *
     * @return
     */
    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Get user by id
     *
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    /**
     * Get user by a token
     *
     * @param token
     * @return
     */
    public User getUserByToken(String token) {
        return userDAO.getUserById(tokenDAO.getUserIdByToken(token));
    }
}
