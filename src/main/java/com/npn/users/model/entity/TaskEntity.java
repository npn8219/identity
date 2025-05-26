package com.npn.users.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.npn.users.enums.TaskPriority;
import com.npn.users.enums.TaskStatus;
import com.npn.users.model.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_entity")
public class TaskEntity extends AbstractEntity {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime deadline;

    @Column
    private LocalDateTime completedDate;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<UserEntity> assignedUsers = new HashSet<>();

    @Column(nullable = false)
    private String status = TaskStatus.PENDING.getValue();

    @Column(nullable = false)
    private String priority = TaskPriority.MEDIUM.getValue();
}
