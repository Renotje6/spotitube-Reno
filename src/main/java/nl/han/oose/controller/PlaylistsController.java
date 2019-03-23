package nl.han.oose.controller;

import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.entity.playlists.Playlist;
import nl.han.oose.service.PlaylistsServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/playlists" )
public class PlaylistsController {

    @Inject
    PlaylistsServiceImpl playlistsService;

    @Path( "" )
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getPlaylists(@QueryParam( "token" ) String token) {
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
        try {
            return Response.ok().entity(playlistsService.addPlaylist(playlistToAdd, token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path( "/{id}" )
    @PUT
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response editPlaylist(Playlist playlist, @PathParam( "id" ) int playlistId, @QueryParam( "token" ) String token) {
        try {
            return Response.ok().entity(playlistsService.editPlaylist(playlist, playlistId, token)).build();
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
