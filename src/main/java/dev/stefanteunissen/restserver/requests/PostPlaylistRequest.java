package dev.stefanteunissen.restserver.requests;

import dev.stefanteunissen.restserver.models.Track;

import java.util.ArrayList;

public class PostPlaylistRequest {
    public int id;
    public String name;
    public boolean owner;
    public ArrayList<Track> tracks;
}
