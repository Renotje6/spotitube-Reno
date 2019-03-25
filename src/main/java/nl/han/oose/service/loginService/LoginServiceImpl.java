package nl.han.oose.service.loginService;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.AccountToken;
import nl.han.oose.persistence.AccountDAO;
import nl.han.oose.persistence.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public AccountToken login(Account account) throws LoginException {
        Account userAccount = accountDAO.getUserAccount(account.getUser());

        if(userAccount != null && userAccount.getPassword().equals(account.getPassword())){
            AccountToken token = tokenDAO.generateNewToken(account.getUser());
            return token;
        } else{
            throw new LoginException("Wrong credentials");
        }
    }
}
