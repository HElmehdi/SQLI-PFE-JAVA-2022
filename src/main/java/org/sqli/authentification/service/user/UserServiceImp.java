package org.sqli.authentification.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sqli.authentification.constant.Headers;
import org.sqli.authentification.constant.Roles;
import org.sqli.authentification.dao.user.UserDAO;
import org.sqli.authentification.entitie.User;
import org.sqli.authentification.exception.AuthException;
import org.sqli.authentification.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImp implements UserService {

    private final UserDAO userDao;
    private final HttpServletRequest request;

    public UserServiceImp(UserDAO userDao, HttpServletRequest request) {
        this.userDao = userDao;
        this.request = request;
    }

    @Override
    public SuccessMessageResponse deleteByLogin(final String login) {
        String adminLogin = request.getHeader(Headers.ADMIN_LOGIN);
        String adminPassword = request.getHeader(Headers.ADMIN_PASSWORD);

        boolean authorized = checkAuthority(adminLogin, adminPassword);
        if(authorized) {
            Long deleted = userDao.deleteByLogin(login);
            log.info("deleted   {}", deleted);
            if(deleted > 0)
                return new SuccessMessageResponse("Login '" + login + "' is deleted");
            else {
                throw  new NotFoundException("Login '" + login + "' is not found");
            }
        }
        throw new AuthException("Unauthorized");

    }

    public boolean checkAuthority(String login, String password) {
        Optional<User> user = userDao.findByLoginAndPassword(login, password);
        return user.isPresent() && user.get().getGroup().getName().equals(Roles.ADMIN);
    }



}
