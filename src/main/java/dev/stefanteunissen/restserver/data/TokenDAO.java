package dev.stefanteunissen.restserver.data;

import dev.stefanteunissen.restserver.DatabaseConnection;
import dev.stefanteunissen.restserver.exceptions.InvalidTokenProvidedException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TokenDAO {
    /**
     * Set the token after logging in
     *
     * @param id
     * @return
     */
    public String setToken(int id) {
        String token = UUID.randomUUID().toString();

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO tokens (token, user_id) " +
                                "VALUES (?,?)")
        ) {
            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return token;
    }

    /**
     * Get the user id from the provided token
     *
     * @param token
     * @return
     */
    public int getUserIdByToken(String token) {
        int resultUserId = -1;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tokens WHERE token=?")
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                resultUserId = resultSet.getInt("user_id");
            resultSet.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        if (resultUserId == -1)
            throw new InvalidTokenProvidedException("Invalid token provided");
        return resultUserId;
    }
}
