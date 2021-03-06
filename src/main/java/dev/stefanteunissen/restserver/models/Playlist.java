package dev.stefanteunissen.restserver.models;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private int ownerId;
    private boolean owner;
    private ArrayList<Track> tracks;

    public Playlist() {
        this.tracks = new ArrayList<>();
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public ArrayList<Track> getTracks() {
        return this.tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int owner_id) {
        this.ownerId = owner_id;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
