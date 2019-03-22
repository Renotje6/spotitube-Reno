package nl.han.oose.service;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.AccountToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    AccountToken login(Account account) throws LoginException;
}
