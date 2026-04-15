-- --------------------------------------------------------
-- 博客系统数据库初始化脚本
-- 字符集：utf8mb4
-- 适用：MySQL 8.0+
-- --------------------------------------------------------

CREATE DATABASE IF NOT EXISTS blog
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE blog;

-- --------------------------------------------------------
-- 1. 文章表
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_article;
CREATE TABLE tbl_article (
    id BIGINT NOT NULL COMMENT '主键ID（雪花算法）',
    title VARCHAR(255) DEFAULT NULL COMMENT '文章标题',
    cover_url VARCHAR(500) DEFAULT NULL COMMENT '封面图片URL',
    content LONGTEXT COMMENT '文章内容（Markdown）',
    create_at DATETIME DEFAULT NULL COMMENT '创建时间',
    categories_id BIGINT DEFAULT NULL COMMENT '分类ID',
    status INT DEFAULT 0 COMMENT '状态：0-已发布，1-草稿',
    is_top TINYINT(1) DEFAULT 0 COMMENT '是否置顶：0-否，1-是',
    views BIGINT DEFAULT 0 COMMENT '浏览量',
    deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除：0-未删除，1-已删除',
    PRIMARY KEY (id),
    KEY idx_categories_id (categories_id),
    KEY idx_create_at (create_at),
    KEY idx_is_top (is_top),
    KEY idx_deleted_status (deleted, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- --------------------------------------------------------
-- 2. 分类表
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_categories;
CREATE TABLE tbl_categories (
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(255) DEFAULT NULL COMMENT '分类名称',
    PRIMARY KEY (id),
    KEY idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

-- --------------------------------------------------------
-- 3. 评论表
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_comment;
CREATE TABLE tbl_comment (
    id BIGINT NOT NULL COMMENT '主键ID',
    content TEXT COMMENT '评论内容',
    name VARCHAR(255) DEFAULT NULL COMMENT '评论者昵称',
    email VARCHAR(255) DEFAULT NULL COMMENT '评论者邮箱',
    url VARCHAR(500) DEFAULT NULL COMMENT '评论者网址',
    create_at DATETIME DEFAULT NULL COMMENT '创建时间',
    love INT DEFAULT 0 COMMENT '点赞数',
    device VARCHAR(255) DEFAULT NULL COMMENT '设备信息',
    address VARCHAR(255) DEFAULT NULL COMMENT 'IP属地',
    ops VARCHAR(255) DEFAULT NULL COMMENT '操作系统',
    page_id BIGINT DEFAULT NULL COMMENT '页面ID（文章ID或留言板ID）',
    PRIMARY KEY (id),
    KEY idx_page_id (page_id),
    KEY idx_create_at (create_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- --------------------------------------------------------
-- 4. 回复表
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_reply;
CREATE TABLE tbl_reply (
    id BIGINT NOT NULL COMMENT '主键ID',
    content TEXT COMMENT '回复内容',
    name VARCHAR(255) DEFAULT NULL COMMENT '回复者昵称',
    email VARCHAR(255) DEFAULT NULL COMMENT '回复者邮箱',
    url VARCHAR(500) DEFAULT NULL COMMENT '回复者网址',
    create_at DATETIME DEFAULT NULL COMMENT '创建时间',
    love INT DEFAULT 0 COMMENT '点赞数',
    device VARCHAR(255) DEFAULT NULL COMMENT '设备信息',
    address VARCHAR(255) DEFAULT NULL COMMENT 'IP属地',
    ops VARCHAR(255) DEFAULT NULL COMMENT '操作系统',
    comment_id BIGINT DEFAULT NULL COMMENT '所属评论ID',
    to_id BIGINT DEFAULT NULL COMMENT '回复对象ID',
    PRIMARY KEY (id),
    KEY idx_comment_id (comment_id),
    KEY idx_create_at (create_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='回复表';

-- --------------------------------------------------------
-- 5. 标签表
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_tag;
CREATE TABLE tbl_tag (
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(255) DEFAULT NULL COMMENT '标签名称',
    PRIMARY KEY (id),
    KEY idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- --------------------------------------------------------
-- 6. 文章-标签关联表
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_tag_article;
CREATE TABLE tbl_tag_article (
    article_id BIGINT NOT NULL COMMENT '文章ID',
    tag_id BIGINT NOT NULL COMMENT '标签ID',
    PRIMARY KEY (article_id, tag_id),
    KEY idx_tag_id (tag_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章标签关联表';

-- --------------------------------------------------------
-- 7. 网站描述/公告表
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_describe;
CREATE TABLE tbl_describe (
    id BIGINT NOT NULL COMMENT '主键ID',
    content TEXT COMMENT '描述内容',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='网站描述表';

-- --------------------------------------------------------
-- 8. 通知表（当前未使用）
-- --------------------------------------------------------
DROP TABLE IF EXISTS tbl_notice;
CREATE TABLE tbl_notice (
    id BIGINT NOT NULL COMMENT '主键ID',
    content TEXT COMMENT '通知内容',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知表';

-- --------------------------------------------------------
-- 9. 站点信息表（单表单行，固定 id=1）
-- --------------------------------------------------------
DROP TABLE IF EXISTS sys_info;
CREATE TABLE sys_info (
    id BIGINT NOT NULL DEFAULT 1 COMMENT '固定主键，始终为1',
    avatar_url VARCHAR(500) DEFAULT NULL COMMENT '站长头像URL',
    personal_profile VARCHAR(500) DEFAULT NULL COMMENT '个人简介',
    project_url VARCHAR(500) DEFAULT NULL COMMENT '项目地址',
    name VARCHAR(255) DEFAULT NULL COMMENT '站长昵称',
    site_name VARCHAR(255) DEFAULT NULL COMMENT '站点名称',
    typing_word VARCHAR(255) DEFAULT NULL COMMENT '首页打字机文字',
    notice VARCHAR(500) DEFAULT NULL COMMENT '站点公告',
    build_time DATETIME DEFAULT NULL COMMENT '建站时间',
    site_view BIGINT DEFAULT 0 COMMENT '站点总访问量',
    last_time DATETIME DEFAULT NULL COMMENT '最后更新时间',
    email VARCHAR(255) DEFAULT NULL COMMENT '联系邮箱',
    icp VARCHAR(255) DEFAULT NULL COMMENT 'ICP备案号',
    gov VARCHAR(255) DEFAULT NULL COMMENT '公安备案号',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='站点信息表';

-- --------------------------------------------------------
-- 10. 访问记录表
-- --------------------------------------------------------
DROP TABLE IF EXISTS sys_view;
CREATE TABLE sys_view (
    view BIGINT DEFAULT 0 COMMENT '当日访问量',
    time DATETIME DEFAULT NULL COMMENT '记录时间',
    KEY idx_time (time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='站点访问记录表';

-- --------------------------------------------------------
-- 11. 用户表
-- --------------------------------------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    id BIGINT NOT NULL COMMENT '主键ID',
    username VARCHAR(255) DEFAULT NULL COMMENT '用户名',
    password VARCHAR(255) DEFAULT NULL COMMENT '密码（BCrypt加密）',
    avatar_url VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
    PRIMARY KEY (id),
    KEY idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- --------------------------------------------------------
-- 12. 角色表
-- --------------------------------------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(255) DEFAULT NULL COMMENT '角色名称',
    role_key VARCHAR(255) DEFAULT NULL COMMENT '角色标识',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- --------------------------------------------------------
-- 13. 菜单/权限表
-- --------------------------------------------------------
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    menu_name VARCHAR(255) DEFAULT NULL COMMENT '菜单名称',
    perm VARCHAR(255) DEFAULT NULL COMMENT '权限标识',
    PRIMARY KEY (id),
    KEY idx_perm (perm)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单权限表';

-- --------------------------------------------------------
-- 14. 用户角色关联表
-- --------------------------------------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
    user_id BIGINT NOT NULL COMMENT '用户ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

-- --------------------------------------------------------
-- 15. 菜单角色关联表
-- --------------------------------------------------------
DROP TABLE IF EXISTS sys_menu_role;
CREATE TABLE sys_menu_role (
    menu_id BIGINT NOT NULL COMMENT '菜单ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (menu_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单角色关联表';

-- ========================================================
-- 初始化数据
-- ========================================================

-- 站点默认信息
INSERT INTO sys_info (id, avatar_url, personal_profile, project_url, name, site_name, typing_word, notice, build_time, site_view, last_time, email, icp, gov)
VALUES (1, NULL, NULL, NULL, '站长', '我的博客', 'Welcome to my blog', NULL, NOW(), 0, NULL, NULL, NULL, NULL);

-- 默认用户（密码分别为 admin / jiaoker）
-- 提示：如需修改密码，请使用 BCryptPasswordEncoder 重新生成 password 字段
INSERT INTO sys_user (id, username, password, avatar_url) VALUES
(1, 'admin', '$2b$12$meOprlhkfcW5QZDkgwQhbeZS9AnnTXdVXUGPb..v3JL1LCKmnnF1O', NULL),
(2, 'jiaoker', '$2b$12$CpLxoAM5ur9aETfzYQtj3OUmlCEdqMzMbmVYYRlivy5rxx.4EwkTG', NULL);

-- 角色
INSERT INTO sys_role (id, name, role_key) VALUES (1, '管理员', 'admin');

-- 菜单权限
INSERT INTO sys_menu (id, menu_name, perm) VALUES
(1, '文章保存/上传', 'sys:article:save'),
(2, '文章删除', 'sys:article:del'),
(3, '文章更新/恢复/置顶', 'sys:article:update'),
(4, '分类删除', 'sys:category:del'),
(5, '分类保存', 'sys:category:save'),
(6, '分类更新', 'sys:category:update'),
(7, '标签删除', 'sys:tag:del'),
(8, '标签更新', 'sys:tag:update'),
(9, '标签保存', 'sys:tag:save'),
(10, '评论删除', 'sys:comment:del');

-- 用户角色关联
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1),
(2, 1);

-- 菜单角色关联（管理员拥有全部权限）
INSERT INTO sys_menu_role (menu_id, role_id) VALUES
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1),
(6, 1), (7, 1), (8, 1), (9, 1), (10, 1);
