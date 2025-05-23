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
@Table(name = "permission_entity")
public class PermissionEntity extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<RoleEntity> roles = new HashSet<>();
}
