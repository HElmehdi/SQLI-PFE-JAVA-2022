package org.sqli.authentification.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.sqli.authentification.dao.user.UserDAO;
import org.sqli.authentification.dao.userGroup.UserGroupDao;
import org.sqli.authentification.dto.UserLoggedInDTO;
import org.sqli.authentification.dto.UserLoginFormDTO;
import org.sqli.authentification.dto.UserRegisterFormDTO;
import org.sqli.authentification.entitie.User;
import org.sqli.authentification.entitie.UserGroup;
import org.sqli.authentification.exception.AuthException;
import org.sqli.authentification.exception.NotFoundException;

@Service
@Slf4j
public class AuthServiceImp implements AuthService {

    private final UserDAO userDao;
    private final UserGroupDao userGroupDao;

    public AuthServiceImp(UserDAO userDao, UserGroupDao userGroupDao) {
        this.userDao = userDao;
        this.userGroupDao = userGroupDao;
    }


    @Override
    public UserLoggedInDTO login(final UserLoginFormDTO userLoginFormDTO) {
        log.info("login attempt with data {}", userLoginFormDTO);
        UserLoggedInDTO userLoggedIn = userDao
                .findByLoginAndPassword(userLoginFormDTO.getLogin(), userLoginFormDTO.getPassword())
                .map(user -> mapToLoggedInDTO(user, new UserLoggedInDTO()))
                .orElseThrow(() -> new AuthException("Authentication error"));

        if(userLoggedIn.getLoginattempts() >= 3) {
            throw new AuthException("You have reached 3 failed authentication attempts, your account will be disabled");
        }
        if(!userLoggedIn.isEnabled()) {
            throw new AuthException("User disabled");
        }

        return userLoggedIn;
    }

    @Override
    public UserLoggedInDTO register(final UserRegisterFormDTO userRegisterFormDTO) {
        log.info("registering new user with data {}", userRegisterFormDTO);
        final UserGroup userGroup = userGroupDao
                .findByName(userRegisterFormDTO.getGroup())
                .orElseThrow(() -> new NotFoundException("Group '" + userRegisterFormDTO.getGroup() + "' is not valid"));

        final User user = User.builder().build();
        mapToEntity(userRegisterFormDTO, user);
        user.setGroup(userGroup);
        try {
            userDao.save(user);
        }
        catch (DataIntegrityViolationException exception) {
            throw new AuthException("Login " +  userRegisterFormDTO.getLogin() + " is not valid");
        }


        return mapToLoggedInDTO(user, new UserLoggedInDTO());
    }


    /**
     * @param user
     * @param userLoggedInDTO
     * @return an object mapped from the input user into UserLoggedInDTO
     */
    private UserLoggedInDTO mapToLoggedInDTO(final User user, final UserLoggedInDTO userLoggedInDTO) {
        userLoggedInDTO.setId(user.getId());
        userLoggedInDTO.setLogin(user.getLogin());
        userLoggedInDTO.setGroup(user.getGroup().getName());
        userLoggedInDTO.setEnabled(user.isEnabled());
        userLoggedInDTO.setLoginattempts(user.getLoginattempts());
        return userLoggedInDTO;
    }


    private User mapToEntity(final UserRegisterFormDTO userRegisterFormDTO, User user) {
        user.setLogin(userRegisterFormDTO.getLogin());
        user.setPassword(userRegisterFormDTO.getPassword());
        return user;
    }

}
