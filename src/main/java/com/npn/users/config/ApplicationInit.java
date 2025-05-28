package com.npn.users.config;

import com.npn.users.enums.UserStatus;
import com.npn.users.model.entity.*;
import com.npn.users.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@Slf4j
public class ApplicationInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;

    @Bean
    @Transactional
    ApplicationRunner applicationRunner() {
        return args -> {

            if (permissionRepository.count() == 0)
                createPermissions();

            if (roleRepository.count() == 0)
                createRoles();

            if (userRepository.count() == 0)
                createUsers();

            showData();
        };
    }

    @Transactional
    protected void createPermissions() {
        List<PermissionEntity> permissions = List.of(
            // Quyền liên quan đến User
            PermissionEntity.builder().name("view_user").description("Quyền xem thông tin người dùng").build(),
            PermissionEntity.builder().name("create_user").description("Quyền tạo người dùng").build(),
            PermissionEntity.builder().name("update_user").description("Quyền cập nhật thông tin người dùng").build(),
            PermissionEntity.builder().name("delete_user").description("Quyền xóa người dùng").build(),

            // Quyền liên quan đến Guest
            PermissionEntity.builder().name("view_guest").description("Quyền xem thông tin khách").build(),
            PermissionEntity.builder().name("create_guest").description("Quyền tạo tài khoản khách").build(),
            PermissionEntity.builder().name("update_guest").description("Quyền cập nhật thông tin khách").build(),
            PermissionEntity.builder().name("delete_guest").description("Quyền xóa tài khoản khách").build(),

            // Quyền liên quan đến Admin
            PermissionEntity.builder().name("view_admin").description("Quyền xem thông tin admin").build(),
            PermissionEntity.builder().name("create_admin").description("Quyền tạo tài khoản admin").build(),
            PermissionEntity.builder().name("update_admin").description("Quyền cập nhật thông tin admin").build(),
            PermissionEntity.builder().name("delete_admin").description("Quyền xóa tài khoản admin").build(),

            // Quyền liên quan đến Role
            PermissionEntity.builder().name("view_role").description("Quyền xem vai trò").build(),
            PermissionEntity.builder().name("create_role").description("Quyền tạo vai trò").build(),
            PermissionEntity.builder().name("update_role").description("Quyền cập nhật vai trò").build(),
            PermissionEntity.builder().name("delete_role").description("Quyền xóa vai trò").build(),

            // Quyền liên quan đến Permission
            PermissionEntity.builder().name("view_permission").description("Quyền xem danh sách quyền").build(),
            PermissionEntity.builder().name("create_permission").description("Quyền tạo quyền mới").build(),
            PermissionEntity.builder().name("update_permission").description("Quyền cập nhật quyền").build(),
            PermissionEntity.builder().name("delete_permission").description("Quyền xóa quyền").build(),

            // Quyền liên quan đến Group
            PermissionEntity.builder().name("create_group").description("Quyền tạo nhóm").build(),
            PermissionEntity.builder().name("view_group").description("Quyền xem nhóm").build(),
            PermissionEntity.builder().name("update_group").description("Quyền cập nhật nhóm").build(),
            PermissionEntity.builder().name("delete_group").description("Quyền xóa nhóm").build(),
            PermissionEntity.builder().name("add_user_to_group").description("Quyền thêm người dùng vào nhóm").build(),
            PermissionEntity.builder().name("remove_user_from_group").description("Quyền xóa người dùng khỏi nhóm").build(),

            // Quyền liên quan đến Task
            PermissionEntity.builder().name("create_task").description("Quyền tạo công việc").build(),
            PermissionEntity.builder().name("view_task").description("Quyền xem công việc").build(),
            PermissionEntity.builder().name("update_task").description("Quyền cập nhật công việc").build(),
            PermissionEntity.builder().name("delete_task").description("Quyền xóa công việc").build()
        );

        permissionRepository.saveAll(permissions);
    }

    @Transactional
    protected void createRoles() {

        List<String> allPermissions = Collections.emptyList();
        List<String> userPermission = Arrays.asList(
                "view_user", "view_guest", "view_admin",
                "create_guest", "update_guest", "update_user",
                "view_role", "view_permission",
                "view_group", "add_user_to_group", "remove_user_from_group",
                "create_task", "view_task", "update_task");
        List<String> guestPermission = Arrays.asList(
                "view_guest", "view_task", "update_task");

        createRole("ADMIN", "Quản trị viên", allPermissions);
        createRole("USER", "Người dùng", userPermission);
        createRole("GUEST", "Khách", guestPermission);
    }

    protected void createRole(String name, String description, List<String> permissions) {

        roleRepository.save(RoleEntity.builder()
                .name(name)
                .description(description)
                .build()
        );

        roleRepository.findByName(name).ifPresent(role -> {
            if (permissions.isEmpty()) {
                var permissionEntities = permissionRepository.findAll();
                role.setPermissions(new HashSet<>(permissionEntities));
            } else {
                var permissionEntities = permissionRepository.findAllByNameIn(permissions);
                role.setPermissions(new HashSet<>(permissionEntities));
            }
            roleRepository.save(role);
        });
    }

    @Transactional
    protected void createUsers() {
        createUser(
                "admin","admin123",
                "Admin","System",
                LocalDate.of(1990,1,1),
                "admin@system.com", List.of("ADMIN","USER"));
        createUser(
                "user_test","user123",
                "Normal","User",
                LocalDate.of(1995,6,15),
                "user@system.com",List.of("USER"));
        createUser(
                "guest_test","guest123",
                "Guest", "User",
                LocalDate.of(2000,12,31),
                "guest@system.com",List.of("GUEST"));
    }

    @Transactional
    protected void createUser(
            String username, String password,
            String firstName, String lastName,
            LocalDate dob, String email, List<String> rolesUser
    ) {
        userRepository.save(UserEntity.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .status(UserStatus.ACTIVE.getValue())
                .dob(dob)
                .build());

        userRepository.findByUsername(username).ifPresent(userSaved -> {
            List<RoleEntity> roles = new ArrayList<>(roleRepository.findAllByNameIn(rolesUser));
            userSaved.setRoles(new HashSet<>(roles));
            userRepository.save(userSaved);
        });
    }

    private void showData() {
        userRepository.findAll().forEach(user -> {
            log.error("User {} has link = {}", user.getUsername(), user.getLink());
            log.info("User {} has ", user.getUsername());
            user.getRoles().forEach(role -> {
                log.warn("role {}, has ", role.getName());
                role.getPermissions().forEach(permission -> {
                    log.warn("permission name = {}", permission.getName());
                });
            });
        });
    }




}
