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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
        log.info("Đã tạo {} permissions", permissions.size());

    }

    @Transactional
    protected void createRoles() {

        // Tạo các role
        roleRepository.save(RoleEntity.builder()
                .name("admin")
                .description("Quản trị viên với toàn quyền trong hệ thống")
                .build()
        );

        roleRepository.save(RoleEntity.builder()
                .name("user")
                .description("Người dùng thông thường với các quyền hạn chế")
                .build()
        );

        roleRepository.save(RoleEntity.builder()
                .name("guest")
                .description("Khách với quyền hạn tối thiểu")
                .build()
        );

        RoleEntity adminRole = roleRepository.findByName("ADMIN").orElse(null);
        if (adminRole != null) {
            List<PermissionEntity> adminPermissions = permissionRepository.findAll();
            adminRole.setPermissions(new HashSet<>(adminPermissions));
            roleRepository.save(adminRole);
        }

        RoleEntity userRole = roleRepository.findByName("USER").orElse(null);
        if (userRole != null) {
            List<PermissionEntity> userPermissions = new ArrayList<>(
                    permissionRepository.findAllByNameIn(Arrays.asList(
                            "view_user", "view_guest", "view_admin",
                            "create_guest", "update_guest", "update_user",
                            "view_role", "view_permission",
                            "view_group", "add_user_to_group", "remove_user_from_group",
                            "create_task", "view_task", "update_task"
                    ))
            );
            userRole.setPermissions(new HashSet<>(userPermissions));
            roleRepository.save(userRole);
        }

        RoleEntity guestRole = roleRepository.findByName("GUEST").orElse(null);
        if (guestRole != null) {
            List<PermissionEntity> guestPermissions = new ArrayList<>(
                    permissionRepository.findAllByNameIn(Arrays.asList(
                            "view_guest", "view_task", "update_task"
                    ))
            );
            guestRole.setPermissions(new HashSet<>(guestPermissions));
            roleRepository.save(guestRole);
        }
    }

    @Transactional
    protected void createUsers() {
        createSampleUser(
                "admin","admin123",
                "Admin","System",
                LocalDate.of(1990,1,1),
                "admin@system.com", List.of("ADMIN","USER"));
        createSampleUser(
                "user_test","user123",
                "Normal","User",
                LocalDate.of(1995,6,15),
                "user@system.com",List.of("USER"));
        createSampleUser(
                "guest_test","guest123",
                "Guest", "User",
                LocalDate.of(2000,12,31),
                "guest@system.com",List.of("GUEST"));
    }

    @Transactional
    protected void createSampleUser(
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

        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntity userSaved = userOptional.get();
            List<RoleEntity> roles = new ArrayList<>(roleRepository.findAllByNameIn(rolesUser));
            userSaved.setRoles(new HashSet<>(roles));
            userRepository.save(userSaved);
        }

        userRepository.findByUsername(username).ifPresent(user -> {
            log.warn("User {}: {}", username, user.getLink());
            if (user.getRoles() != null && !user.getRoles().isEmpty())
                user.getRoles().forEach(role -> log.warn("Role: {}", role.getName()));
        });
    }

    private void showData() {

        List<RoleEntity> roles = roleRepository.findAll();
        if (!roles.isEmpty()) {
            log.warn("All role = ");
            roles.forEach(role -> {
                log.warn("{} permissions = ", role.getName());
                if (!role.getPermissions().isEmpty())
                    role.getPermissions().forEach(permission -> log.warn(permission.toString()));
            });
        }

        List<UserEntity> users = userRepository.findAll();
        if (!users.isEmpty()) {
            log.warn("all user = ");
            users.forEach(user -> {
                if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                    String rolesUser = user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.joining(", "));
                    log.warn("{} has link = {} and roles = {}", user.getUsername(), user.getLink(), rolesUser);
                }
            });
        }
    }
}
