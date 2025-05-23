package com.npn.users.repository;

import com.npn.users.model.entity.RoleEntity;
import com.npn.users.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT COUNT(*) FROM UserEntity")
    long count();

    Optional<UserEntity> findByUsername(String username);

    Optional<Object> findByRoles(Set<RoleEntity> roles);
}
