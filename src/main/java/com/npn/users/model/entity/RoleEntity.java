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
@Table(name = "role_entity")
public class RoleEntity extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<PermissionEntity> permissions = new HashSet<>();

}
