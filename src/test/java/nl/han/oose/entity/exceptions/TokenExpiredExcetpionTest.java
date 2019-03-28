package nl.han.oose.entity.exceptions;

import org.junit.Test;

public class TokenExpiredExcetpionTest {

    @Test (expected = TokenExpiredException.class )
    public void testConstructorWithMessageAndCause()throws TokenExpiredException{
        throw new TokenExpiredException("TokenExpired", new NullPointerException("reason of this exception"));
    }

    @Test (expected = TokenExpiredException.class)
    public void testConstructorWithOnlyCause() throws TokenExpiredException {
        throw new TokenExpiredException(new NullPointerException("reason of this exception"));
    }

    @Test(expected = TokenExpiredException.class)
    public void testConstructorWithMessage() throws TokenExpiredException {
        throw new TokenExpiredException("Token Expired");
    }

    @Test(expected = TokenExpiredException.class)
    public void testEmptyConstructor() throws TokenExpiredException {
        throw new TokenExpiredException();
    }
}
