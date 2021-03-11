package dev.stefanteunissen.restserver.resources;

import dev.stefanteunissen.restserver.models.User;
import dev.stefanteunissen.restserver.requests.PostPlaylistRequest;
import dev.stefanteunissen.restserver.requests.PostTrackToPlaylistRequest;
import dev.stefanteunissen.restserver.services.PlaylistService;
import dev.stefanteunissen.restserver.services.TrackService;
import dev.stefanteunissen.restserver.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/playlists")
public class PlaylistResource {
    @Inject
    public UserService userService;
    @Inject
    public PlaylistService playlistService;
    @Inject
    public TrackService trackService;

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        User user = userService.getUserByToken(token);

        return Response.ok(playlistService.getAllPlaylistsWithLength(user.getId())).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        User user = userService.getUserByToken(token);

        return Response.ok(trackService.getAllTracksFromPlaylist(id)).build();
    }

    @DELETE
    @Path("/{id}/tracks/{trackId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("id") int id, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        User user = userService.getUserByToken(token);

        trackService.deleteTrackFromPlaylist(id, trackId);

        return Response.ok(trackService.getAllTracksFromPlaylist(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        User user = userService.getUserByToken(token);

        playlistService.deletePlaylist(id);

        return Response.ok(playlistService.getAllPlaylistsWithLength(user.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@PathParam("id") int id, @QueryParam("token") String token, final PostPlaylistRequest request) {
        User user = userService.getUserByToken(token);

        playlistService.updatePlaylist(id, request.name);

        return Response.ok(playlistService.getAllPlaylistsWithLength(user.getId())).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, final PostPlaylistRequest request) {
        User user = userService.getUserByToken(token);
        System.out.println(user.getId());

        playlistService.addPlaylist(user.getId(), request.name);

        return Response.created(URI.create("")).entity(playlistService.getAllPlaylistsWithLength(user.getId())).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int id, @QueryParam("token") String token, final PostTrackToPlaylistRequest request) {
        User user = userService.getUserByToken(token);

        playlistService.addTrackToPlaylist(id, request.id, request.offlineAvailable);

        return Response.created(URI.create("")).entity(trackService.getAllTracksFromPlaylist(id)).build();
    }
}
