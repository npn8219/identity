-- Nhóm 3: Tạo bảng

CREATE TABLE group_entity
(
    is_active   bit          NOT NULL,
    id          bigint       NOT NULL,
    description TEXT,
    name        varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE group_entity_seq
(
    next_val bigint
) ENGINE=InnoDB;

CREATE TABLE permission_entity
(
    is_active   bit          NOT NULL,
    id          bigint       NOT NULL,
    description TEXT,
    name        varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE permission_entity_seq
(
    next_val bigint
) ENGINE=InnoDB;

CREATE TABLE role_entity
(
    is_active   bit          NOT NULL,
    id          bigint       NOT NULL,
    description TEXT,
    name        varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE role_entity_seq
(
    next_val bigint
) ENGINE=InnoDB;

CREATE TABLE role_permission
(
    permission_id bigint NOT NULL,
    role_id       bigint NOT NULL,
    PRIMARY KEY (permission_id, role_id)
) ENGINE=InnoDB;

CREATE TABLE task_entity
(
    is_active      bit          NOT NULL,
    completed_date datetime(6),
    deadline       datetime(6),
    id             bigint       NOT NULL,
    description    TEXT,
    priority       varchar(255) NOT NULL,
    status         varchar(255) NOT NULL,
    title          varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE task_entity_seq
(
    next_val bigint
) ENGINE=InnoDB;

CREATE TABLE user_entity
(
    dob        date,
    is_active  bit          NOT NULL,
    id         bigint       NOT NULL,
    email      varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    status     varchar(255) NOT NULL,
    token      varchar(255),
    username   varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user_entity_seq
(
    next_val bigint
) ENGINE=InnoDB;

CREATE TABLE user_group
(
    group_id bigint NOT NULL,
    user_id  bigint NOT NULL,
    PRIMARY KEY (group_id, user_id)
) ENGINE=InnoDB;

CREATE TABLE user_role
(
    role_id bigint NOT NULL,
    user_id bigint NOT NULL,
    PRIMARY KEY (role_id, user_id)
) ENGINE=InnoDB;

CREATE TABLE user_task
(
    task_id bigint NOT NULL,
    user_id bigint NOT NULL,
    PRIMARY KEY (task_id, user_id)
) ENGINE=InnoDB;

-- Nhóm 4: Chèn dữ liệu khởi tạo

INSERT INTO group_entity_seq
VALUES (1);
INSERT INTO permission_entity_seq
VALUES (1);
INSERT INTO role_entity_seq
VALUES (1);
INSERT INTO task_entity_seq
VALUES (1);
INSERT INTO user_entity_seq
VALUES (1);

-- Nhóm 5: Tạo Unique Key

ALTER TABLE permission_entity
    ADD CONSTRAINT UKie6sou3ikjjbtrtnofrr4ywmx UNIQUE (name);
ALTER TABLE role_entity
    ADD CONSTRAINT UK2uqxlfg1dlwv0mtewrokr23ou UNIQUE (name);
ALTER TABLE user_entity
    ADD CONSTRAINT UK4xad1enskw4j1t2866f7sodrx UNIQUE (email);
ALTER TABLE user_entity
    ADD CONSTRAINT UKdn94k3s93vqlu1ufqus4t6y13 UNIQUE (token);
ALTER TABLE user_entity
    ADD CONSTRAINT UK2jsk4eakd0rmvybo409wgwxuw UNIQUE (username);

-- Nhóm 6: Thêm khóa ngoại

ALTER TABLE role_permission
    ADD CONSTRAINT FKky736fypjdg9w2fj1xv4m7yd1 FOREIGN KEY (permission_id) REFERENCES permission_entity (id);
ALTER TABLE role_permission
    ADD CONSTRAINT FKgu0wxwfjwk4n3n3dopx2auciy FOREIGN KEY (role_id) REFERENCES role_entity (id);
ALTER TABLE user_group
    ADD CONSTRAINT FKigraic5s1w8kvvvppwltekao2 FOREIGN KEY (group_id) REFERENCES group_entity (id);
ALTER TABLE user_group
    ADD CONSTRAINT FKyiga1urvcdvpting421gc28m FOREIGN KEY (user_id) REFERENCES user_entity (id);
ALTER TABLE user_role
    ADD CONSTRAINT FK66rggwh2ht8u9ig7hvam1jvai FOREIGN KEY (role_id) REFERENCES role_entity (id);
ALTER TABLE user_role
    ADD CONSTRAINT FK79ltvrbu1ni2ad7w7i9vers1k FOREIGN KEY (user_id) REFERENCES user_entity (id);
ALTER TABLE user_task
    ADD CONSTRAINT FK4xtx76k1al69nhud5m5ha0y4u FOREIGN KEY (user_id) REFERENCES user_entity (id);
ALTER TABLE user_task
    ADD CONSTRAINT FKhxb4bpnad6jgqgki78208wpt5 FOREIGN KEY (task_id) REFERENCES task_entity (id);