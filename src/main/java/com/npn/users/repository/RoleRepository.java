package com.npn.users.repository;

import com.npn.users.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);

    @Query(value = "SELECT COUNT(*) FROM RoleEntity")
    long count();

    Set<RoleEntity> findAllByNameIn(Collection<String> name);

    Optional<RoleEntity> findByNameAndIdNot(String name, Long id);
}
