package org.sqli.authentification.dao.user;

import org.sqli.authentification.entitie.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByLoginAndPassword(final String login, final String password);
    Long deleteByLogin(final String login);
    Long save(final User user);
}
