package nl.han.oose.controller;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.AccountToken;
import nl.han.oose.service.loginService.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

@RunWith( MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    LoginServiceImpl loginService;

    @InjectMocks
    LoginController sut;

    @Test
    public void testThatsuccessfulLoginReturnsOKStatus() throws LoginException {
        Account account = new Account();
        AccountToken token = new AccountToken("1234-1234-1234", "rovers");
        Mockito.when(loginService.login(account)).thenReturn(token);
        Response response = sut.login(account);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(token.getClass(), response.getEntity().getClass());
    }

    @Test
    public void testThatLoginExceptionIsThrown() throws LoginException {
        Account account = new Account();
        Mockito.when(loginService.login(account)).thenThrow(new LoginException());
        Response response = sut.login(account);
        Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
}
