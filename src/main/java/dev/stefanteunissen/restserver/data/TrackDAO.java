package dev.stefanteunissen.restserver.data;

import dev.stefanteunissen.restserver.DatabaseConnection;
import dev.stefanteunissen.restserver.models.Track;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackDAO {
    /**
     * Retrieve all track rows from a playlist
     *
     * @param playlistId
     * @return
     */
    public ArrayList<Track> getAllTracksFromPlaylist(int playlistId) {
        ArrayList<Track> resultList = new ArrayList<>();

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT tracks.*, playlist_tracks.offline_available " +
                                "FROM playlists " +
                                "LEFT JOIN playlist_tracks ON playlist_tracks.playlist_id=playlists.id " +
                                "LEFT JOIN tracks ON tracks.id=playlist_tracks.track_id " +
                                "WHERE playlists.id=?")
        ) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1) == null)
                    continue;
                Track track = new Track();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setAlbum(resultSet.getString("album"));
                track.setPublicationDate(resultSet.getDate("publication_date"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offline_available"));
                resultList.add(track);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return resultList;
    }

    /**
     * Delete a track from a playlist
     *
     * @param playlistId
     * @param trackId
     * @return
     */
    public boolean deleteTrackFromPlaylist(int playlistId, int trackId) {
        boolean deleted = false;

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist_tracks WHERE playlist_id=? AND track_id=?")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            deleted = preparedStatement.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return deleted;
    }

    /**
     * Get all tracks from the database
     *
     * @return
     */
    public ArrayList<Track> getAllTracks() {
        ArrayList<Track> resultList = new ArrayList<>();

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM tracks");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                if (resultSet.getString(1) == null)
                    continue;
                Track track = new Track();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setAlbum(resultSet.getString("album"));
                track.setPublicationDate(resultSet.getDate("publication_date"));
                track.setDescription(resultSet.getString("description"));
                resultList.add(track);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return resultList;
    }

    /**
     * Get all tracks that are not yet in the playlist with the given id
     *
     * @param id
     * @return
     */
    public ArrayList<Track> getAllTracksForPlaylist(int id) {
        ArrayList<Track> resultList = new ArrayList<>();

        try (
                Connection connection = new DatabaseConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM tracks as tr " +
                                "WHERE NOT EXISTS (SELECT tracks.* " +
                                "FROM tracks " +
                                "LEFT JOIN playlist_tracks ON tracks.id=playlist_tracks.track_id " +
                                "WHERE playlist_tracks.playlist_id=? AND tracks.id=tr.id)")
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1) == null)
                    continue;
                Track track = new Track();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setAlbum(resultSet.getString("album"));
                track.setPublicationDate(resultSet.getDate("publication_date"));
                track.setDescription(resultSet.getString("description"));
                resultList.add(track);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return resultList;
    }
}
