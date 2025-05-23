package com.npn.users.repository;

import com.npn.users.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Set<PermissionEntity> findAllByNameIn(Collection<String> names);

    @Query(value = "SELECT COUNT(*) FROM PermissionEntity")
    long count();
}
