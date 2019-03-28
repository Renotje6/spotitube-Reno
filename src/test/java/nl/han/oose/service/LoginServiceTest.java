package nl.han.oose.service;

import nl.han.oose.controller.LoginController;
import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.AccountToken;
import nl.han.oose.persistence.AccountDAO;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.service.loginService.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

@RunWith( MockitoJUnitRunner.class )
public class LoginServiceTest {

    @Mock
    AccountDAO accountDAO;

    @Mock
    TokenDAO tokenDAO;

    @InjectMocks
    LoginServiceImpl sut;

    /**
     * Test for login
     */
    @Test
    public void testThatAccountTokenIsReturned() throws LoginException {
        Account account = new Account("reno", "rovers");
        AccountToken token = new AccountToken("123-123-123", "reno");

        Mockito.when(accountDAO.getUserAccount(account.getUser())).thenReturn(account);
        Mockito.when(tokenDAO.generateNewToken(account.getUser())).thenReturn(token);

        AccountToken returnedToken = sut.login(account);
        Assert.assertEquals(token.getToken(), returnedToken.getToken());
    }

    @Test(expected = LoginException.class)
    public void testThatExceptionIsThrown() throws LoginException {
        Account account = new Account("reno", "rovers");

        Mockito.when(accountDAO.getUserAccount("wrong username")).thenReturn(account);

        sut.login(new Account("wrong username", "test"));
    }
}
