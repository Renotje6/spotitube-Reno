package nl.han.oose.controller;


import nl.han.oose.entity.exceptions.TokenExpiredException;
import nl.han.oose.service.trackService.TrackServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    TrackServiceImpl trackService;



    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token){
        if(token.isEmpty() || playlistId <= 0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try{
            return Response.ok().entity(trackService.getAllTracksNotInPlaylist(playlistId, token)).build();
        } catch (TokenExpiredException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
