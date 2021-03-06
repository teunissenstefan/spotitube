package dev.stefanteunissen.restserver.data;

import dev.stefanteunissen.restserver.DatabaseConnection;
import dev.stefanteunissen.restserver.models.Playlist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {
    /**
     * Retrieve all playlist rows
     *
     * @return
     */
    public ArrayList<Playlist> getAllPlaylists() {
        ArrayList<Playlist> resultList = new ArrayList<>();

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwnerId(resultSet.getInt("owner_id"));
                resultList.add(playlist);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return resultList;
    }

    /**
     * Delete a playlist by id
     *
     * @param id
     * @return
     */
    public boolean deletePlaylist(int id) {
        boolean deleted = false;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlists WHERE id=?")
        ) {
            preparedStatement.setInt(1, id);
            deleted = preparedStatement.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return deleted;
    }

    /**
     * Update a playlist
     *
     * @param id
     * @param name
     * @return
     */
    public boolean updatePlaylist(int id, String name) {
        boolean updated = false;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE playlists " +
                                "SET name=? WHERE id=?")
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            updated = preparedStatement.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return updated;
    }

    /**
     * Add a playlist
     *
     * @param ownerId
     * @param name
     * @return
     */
    public boolean addPlaylist(int ownerId, String name) {
        boolean added = false;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO playlists " +
                                "(name, owner_id) " +
                                "VALUES (?,?)")
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, ownerId);
            added = preparedStatement.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return added;
    }

    /**
     * Add a track to a playlist
     *
     * @param playlistId
     * @param trackId
     * @param offlineAvailable
     * @return
     */
    public boolean addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable) {
        boolean added = false;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO playlist_tracks " +
                                "(track_id, playlist_id, offline_available) " +
                                "VALUES (?,?,?)")
        ) {
            preparedStatement.setInt(1, trackId);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.setBoolean(3, offlineAvailable);
            added = preparedStatement.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return added;
    }
}
