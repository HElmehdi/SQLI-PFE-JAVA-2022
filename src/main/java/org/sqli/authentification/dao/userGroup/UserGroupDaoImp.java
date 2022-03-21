package org.sqli.authentification.dao.userGroup;

import org.springframework.stereotype.Repository;
import org.sqli.authentification.entitie.UserGroup;
import org.sqli.authentification.repository.UserGroupRepository;

import java.util.Optional;

@Repository
public class UserGroupDaoImp implements UserGroupDao{

    private final UserGroupRepository userGroupRepository;

    public UserGroupDaoImp(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public Optional<UserGroup> findByName(String name) {
        return userGroupRepository.findByName(name);
    }
}
