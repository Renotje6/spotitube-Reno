package nl.han.oose.controller;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.playlists.Playlist;
import nl.han.oose.entity.tracks.Track;
import nl.han.oose.service.playlistService.PlaylistsServiceImpl;
import nl.han.oose.service.trackService.TrackServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path( "/playlists" )
public class PlaylistsController {

    @Inject
    PlaylistsServiceImpl playlistsService;

    @Inject
    TrackServiceImpl trackService;

    @Path( "" )
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getPlaylists(@QueryParam( "token" ) String token) {
        if (token.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistsService.getAllPlaylists(token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path( "/{id}" )
    @DELETE
    @Produces( MediaType.APPLICATION_JSON )
    public Response deletePlaylist(@PathParam( "id" ) int playlistId, @QueryParam( "token" ) String token) {
        if (token.isEmpty() || playlistId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistsService.deletePlaylist(playlistId, token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path( "" )
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response addPlaylist(Playlist playlistToAdd, @QueryParam( "token" ) String token) {
        if (token.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.CREATED).entity(playlistsService.addPlaylist(playlistToAdd, token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path( "/{id}" )
    @PUT
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response editPlaylist(Playlist playlist, @PathParam( "id" ) int playlistId, @QueryParam( "token" ) String token) {
        if (token.isEmpty() || playlistId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistsService.editPlaylist(playlist, playlistId, token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path( "/{id}/tracks" )
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getAllTracksInPlaylist(@PathParam( "id" ) int playlistId, @QueryParam( "token" ) String token) {
        if (token.isEmpty() || playlistId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(trackService.getAllTracksInPlaylist(playlistId, token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path( "/{playlistId}/tracks/{trackId}" )
    @DELETE
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteTrackFromPlaylist(@PathParam( "playlistId" ) int playlistId, @PathParam( "trackId" ) int trackId, @QueryParam( "token" ) String token) {
        if (token.isEmpty() || playlistId <= 0 || trackId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(trackService.deleteTrackFromPlaylist(playlistId, trackId, token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path( "/{id}/tracks" )
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response addTrackToPlaylist(Track track, @PathParam("id") int playlistId, @QueryParam( "token" ) String token) {
        if (token.isEmpty() || playlistId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try{
            return Response.ok().entity(trackService.addTrackToPlaylist(track, playlistId, token)).build();
        } catch(TokenExpiredException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
