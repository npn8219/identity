package com.npn.users.model.entity.base;

import com.npn.users.model.entity.PermissionEntity;
import com.npn.users.model.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractEntity extends IdLessAbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private boolean isActive;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> objEffectiveClass = o instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() :
                o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != objEffectiveClass) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode() :
                getClass().hashCode();
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (this instanceof PermissionEntity permission) {
            if (permission.getName() != null) {
                permission.setName(permission.getName().toUpperCase());
            }
        } else if (this instanceof RoleEntity role) {
            if (role.getName() != null) {
                role.setName(role.getName().toUpperCase());
            }
        }
    }

}
