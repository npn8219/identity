package com.npn.users.repository;

import com.npn.users.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query(value = "SELECT COUNT(*) FROM TaskEntity")
    long count();
}
