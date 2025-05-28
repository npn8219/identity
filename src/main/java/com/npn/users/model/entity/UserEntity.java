package com.npn.users.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.npn.users.enums.UserStatus;
import com.npn.users.model.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "user_entity")
public class UserEntity extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true)
    private LocalDate dob;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = false)
    private String status = UserStatus.ACTIVE.getValue();

    @Column(unique = true)
    private String token;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<GroupEntity> groups = new HashSet<>();

    @ManyToMany(mappedBy = "assignedUsers")
    @JsonIgnore
    @ToString.Exclude
    private Set<TaskEntity> tasks = new HashSet<>();


    public String getLink() {
        return "/users/" + getId();
    }

}
