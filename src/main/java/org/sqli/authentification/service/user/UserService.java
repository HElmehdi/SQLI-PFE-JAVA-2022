package org.sqli.authentification.service.user;


public interface UserService {
    SuccessMessageResponse deleteByLogin(final String login);
}
