package nl.han.oose.controller;


import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.service.TrackServiceImpl;

import javax.annotation.Generated;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class TrackController {

    @Inject
    TrackServiceImpl trackService;

    @Path("/{id}/tracks")
    @GET
    @Produces ( MediaType.APPLICATION_JSON )
    public Response getAllTracksInPlaylist(@PathParam("id") int playlistID, @QueryParam("token") String token){
        try{
            return Response.ok().entity(trackService.getAllTrackInPlaylist(playlistID, token)).build();
        } catch (TokenExpiredException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
