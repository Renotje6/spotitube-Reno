package nl.han.oose.controller;

import nl.han.oose.entity.account.Account;
import nl.han.oose.service.loginService.LoginServiceImpl;

import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;

@Path("/")
public class LoginController {

    @Inject
    private LoginServiceImpl loginService;

    @Path("login")
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response login(Account account){
        try {
            return Response.ok().entity(loginService.login(account)).build();
        } catch (LoginException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
