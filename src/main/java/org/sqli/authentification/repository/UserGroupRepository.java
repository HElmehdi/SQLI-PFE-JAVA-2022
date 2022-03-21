package org.sqli.authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sqli.authentification.entitie.UserGroup;

import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    Optional<UserGroup> findByName(String name);
}
