package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.PlaylistDAO;
import dev.stefanteunissen.restserver.data.TrackDAO;
import dev.stefanteunissen.restserver.models.Playlist;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlaylistService {
    @Inject
    public PlaylistDAO playlistDAO;
    @Inject
    public TrackDAO trackDAO;

    /**
     * Get all playlists
     *
     * @param userId
     * @return
     */
    public ArrayList<Playlist> getAllPlaylists(int userId) {
        ArrayList<Playlist> returnList = playlistDAO.getAllPlaylists();
        for (Playlist playlist : returnList) {
            playlist.setOwner(playlist.getOwnerId() == userId);
            playlist.setTracks(trackDAO.getAllTracksFromPlaylist(playlist.getId()));
        }
        return returnList;
    }

    /**
     * Get all playlists with the total length variable
     *
     * @param userId
     * @return
     */
    public Map<String, Object> getAllPlaylistsWithLength(int userId) {
        ArrayList<Playlist> playlists = this.getAllPlaylists(userId);
        Map<String, Object> map = new HashMap<>();
        int totalLength = 0;
        for (Playlist plist : playlists)
            for (int j = 0; j < plist.getTracks().size(); j++)
                totalLength += plist.getTracks().get(j).getDuration();

        map.put("playlists", playlists);
        map.put("length", totalLength);
        return map;
    }

    /**
     * Delete a playlist
     *
     * @param id
     * @return
     */
    public boolean deletePlaylist(int id) {
        return playlistDAO.deletePlaylist(id);
    }

    /**
     * Update a playlist
     *
     * @param id
     * @param name
     * @return
     */
    public boolean updatePlaylist(int id, String name) {
        return playlistDAO.updatePlaylist(id, name);
    }

    /**
     * Add a playlist
     *
     * @param ownerId
     * @param name
     * @return
     */
    public boolean addPlaylist(int ownerId, String name) {
        return playlistDAO.addPlaylist(ownerId, name);
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
        return playlistDAO.addTrackToPlaylist(playlistId, trackId, offlineAvailable);
    }
}
