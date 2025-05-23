package com.npn.users.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.npn.users.model.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "group_entity")
public class GroupEntity extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<UserEntity> users = new HashSet<>();

}
