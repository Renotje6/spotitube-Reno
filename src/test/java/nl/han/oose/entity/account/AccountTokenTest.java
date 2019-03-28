package nl.han.oose.entity.account;

import nl.han.oose.entity.account.AccountToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTokenTest {
    AccountToken accountToken;

    @Before
    public void init() {
        accountToken = new AccountToken("123-123-123", "username");
    }

    @Test
    public void testThatTokenHasBeenChanged() {
        String newToken = "456-456-456";
        accountToken.setToken(newToken);

        Assert.assertEquals(newToken, accountToken.getToken());
    }

    @Test
    public void testThatUsernameHasBeenChanged(){
        String newUsername = "newUsername";
        accountToken.setUser(newUsername);

        Assert.assertEquals(newUsername, accountToken.getUser());
    }

    @Test
    public void testEmptyConstructor(){
        new AccountToken();
    }
}
