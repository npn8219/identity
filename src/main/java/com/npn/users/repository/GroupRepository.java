package com.npn.users.repository;

import com.npn.users.model.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM GroupEntity")
    long count();
}
