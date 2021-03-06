package dev.stefanteunissen.restserver.services;

import dev.stefanteunissen.restserver.data.TrackDAO;
import dev.stefanteunissen.restserver.models.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrackService {
    public TrackDAO trackDAO = new TrackDAO();

    /**
     * Get all tracks from a playlist
     *
     * @param playlistId
     * @return
     */
    public Map<String, Object> getAllTracksFromPlaylist(int playlistId) {
        ArrayList<Track> tracks = trackDAO.getAllTracksFromPlaylist(playlistId);

        Map<String, Object> map = new HashMap<>();
        int totalLength = 0;
        for (Track plist : tracks)
            totalLength += plist.getDuration();
        map.put("tracks", tracks);
        map.put("length", totalLength);

        return map;
    }

    /**
     * Delete a track from a playlist
     *
     * @param playlistId
     * @param trackId
     * @return
     */
    public boolean deleteTrackFromPlaylist(int playlistId, int trackId) {
        return trackDAO.deleteTrackFromPlaylist(playlistId, trackId);
    }

    /**
     * Get all tracks
     *
     * @return
     */
    public ArrayList<Track> getAllTracks() {
        return trackDAO.getAllTracks();
    }

    /**
     * Get all tracks that are not yet in the playlist with the given id
     *
     * @param id
     * @return
     */
    public ArrayList<Track> getAllTracksForPlaylist(int id) {
        return trackDAO.getAllTracksForPlaylist(id);
    }
}
