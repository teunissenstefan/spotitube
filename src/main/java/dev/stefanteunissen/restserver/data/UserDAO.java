package dev.stefanteunissen.restserver.data;

import dev.stefanteunissen.restserver.DatabaseConnection;
import dev.stefanteunissen.restserver.exceptions.UserNotFoundException;
import dev.stefanteunissen.restserver.models.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    /**
     * Retrieve all user rows
     *
     * @return
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> resultList = new ArrayList<>();

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                resultList.add(new User(resultSet.getInt("id"), resultSet.getString("user"), resultSet.getString("password")));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return resultList;
    }

    /**
     * Retrieve a user record by id
     *
     * @param id
     * @return
     */
    public User getUserById(int id) {
        User resultUser = null;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?")
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                resultUser = new User(resultSet.getInt("id"), resultSet.getString("user"), resultSet.getString("password"));
            resultSet.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        if (resultUser == null)
            throw new UserNotFoundException("User with ID: " + id + " was not found");
        return resultUser;
    }

    /**
     * Retrieve a user record by username
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        User resultUser = null;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user=?")
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                resultUser = new User(resultSet.getInt("id"), resultSet.getString("user"), resultSet.getString("password"));
            resultSet.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        if (resultUser == null)
            throw new UserNotFoundException("User with username: " + username + " was not found");
        return resultUser;
    }
}
