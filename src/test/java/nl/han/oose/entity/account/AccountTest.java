package nl.han.oose.entity.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    Account account;

    @Before
    public void init() {
        account = new Account("tester", "secretPass");
    }

    @Test
    public void testThatPasswordHasBeenChanged() {
        account.setPassword("newPassword");

        Assert.assertEquals("newPassword", account.getPassword());
    }

    @Test
    public void setThatUsernameHasBeenChanged() {
        String newUsername = "randomUsername";
        account.setUser(newUsername);

        Assert.assertEquals(newUsername, account.getUser());
    }

    @Test
    public void testThatEmptyConstructorWorks(){
        Account account = new Account();

        Assert.assertEquals(Account.class, account.getClass());
    }
}
