# Project quản lí user. 

### AbstractEntity:
    id
    createdBy
    createdAt
    updatedBy
    updatedAt
    softDeleted

### Entity extends AbstractEntity:
    User:
      - username
      - password
      - name
      - dob
      - role
      - isActive
    Role:
      - name
      - description
      - isActive
    Permission:
      - name
      - description
      - isActive
    Group:
      - user
      - name
      - description
      - isActive
    Task:
      - user
      - title
      - description
      - assignedBy
      - deadline
      - priority 

### Relation Tables:
    UserRole:
      - userId
      - roleId
      - assignedAt
    RolePermission:
      - roleId
      - permissionId
      - assignedAt
    UserTask:
      - userId
      - taskId
      - status
      - assignedAt
    GroupUser:
      - groupId
      - userId
      - joinedAt

### Priority base:
    High,
    Medium, 
    Low

### Status base:
    pending, 
    viewed, 
    completed, 
    cancelled

### Role base:
    admin
    user
    guest

### Permission base:
    admin:
        view_user
        view_guest
        view_admin
        create_admin
        create_user
        create_guest
        update_admin
        update_user
        update_guest
        delete_admin
        delete_user
        delete_guest
        view_role
        view_permission
        create_role
        create_permission
        delete_role
        delete_permission
        update_role
        update_permission
        create_group
        view_group
        update_group
        delete_group
        add_user_to_group
        remove_user_from_group
        create_task
        view_task
        update_task
        delete_task

    user:
        view_user
        view_guest
        view_admin
        create_guest
        update_guest
        update_user
        view_role
        view_permission
        view_group
        create_task
        view_task
        update_task

    guest:
        view_guest
        view_task
        update_task

| PERMISSION                | ADMIN | USER | GUEST |
|:-------------------------|:-----:|:----:|:-----:|
| `create_admin`           |   X   |      |       |
| `view_admin`             |   X   |  X   |       |
| `update_admin`           |   X   |      |       |
| `delete_admin`           |   X   |      |       |
| `create_user`            |   X   |      |       |
| `view_user`              |   X   |  X   |       |
| `update_user`            |   X   |  X   |       |
| `delete_user`            |   X   |      |       |
| `create_guest`           |   X   |  X   |       |
| `view_guest`             |   X   |  X   |   X   |
| `update_guest`           |   X   |  X   |       |
| `delete_guest`           |   X   |      |       |
| `view_role`              |   X   |  X   |       |
| `create_role`            |   X   |      |       |
| `update_role`            |   X   |      |       |
| `delete_role`            |   X   |      |       |
| `view_permission`        |   X   |  X   |       |
| `create_permission`      |   X   |      |       |
| `update_permission`      |   X   |      |       |
| `delete_permission`      |   X   |      |       |
| `view_task`              |   X   |  X   |   X   |
| `create_task`            |   X   |  X   |       |
| `update_task`            |   X   |  X   |   X   |
| `delete_task`            |   X   |      |       |
| `view_group`             |   X   |  X   |       |
| `create_group`           |   X   |      |       |
| `update_group`           |   X   |      |       |
| `delete_group`           |   X   |      |       |
| `add_user_to_group`      |   X   |  X   |       |
| `remove_user_from_group`   |   X   |  X   |       |