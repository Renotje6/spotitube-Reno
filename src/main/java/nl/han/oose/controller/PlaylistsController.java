package nl.han.oose.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getPlaylists(@QueryParam("token") String token){
        try{

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
