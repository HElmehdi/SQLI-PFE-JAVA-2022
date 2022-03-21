package org.sqli.authentification.dao.user;

import org.springframework.stereotype.Repository;
import org.sqli.authentification.entitie.User;
import org.sqli.authentification.repository.UserRepository;

import java.util.Optional;

@Repository
public class UserDAOImp implements UserDAO {

    private final UserRepository userRepository;

    public UserDAOImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public Long deleteByLogin(String login) {
        return userRepository.deleteByLogin(login);
    }

    @Override
    public Long save(User user) {
        return userRepository.save(user).getId();
    }
}