package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.models.Track;
import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.services.TrackService;
import dev.stefanteunissen.restserver.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("/tracks")
public class TrackResource {
    @Inject
    public UserService userService;
    @Inject
    public TrackService trackService;

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksForPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist) {
        User user = userService.getUserByToken(token);

        ArrayList<Track> tracks = trackService.getAllTracksForPlaylist(forPlaylist);

        Map<String, Object> map = new HashMap<>();
        map.put("tracks", tracks);
        return Response.ok(map).build();
    }
}
