-- drop.sql

SET
FOREIGN_KEY_CHECKS = 0; -- Tắt kiểm tra khóa ngoại để tránh lỗi khi xóa bảng

DROP TABLE IF EXISTS user_task;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user_group;
DROP TABLE IF EXISTS task_entity;
DROP TABLE IF EXISTS task_entity_seq;
DROP TABLE IF EXISTS user_entity;
DROP TABLE IF EXISTS user_entity_seq;
DROP TABLE IF EXISTS `groups`; -- Escaping backticks để tránh xung đột với từ khóa SQL
DROP TABLE IF EXISTS role_permission;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS role_entity_seq;
DROP TABLE IF EXISTS permission_entity;
DROP TABLE IF EXISTS permission_entity_seq;

SET
FOREIGN_KEY_CHECKS = 1; -- Bật lại kiểm tra khóa ngoại