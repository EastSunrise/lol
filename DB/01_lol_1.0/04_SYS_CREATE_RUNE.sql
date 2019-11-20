-- 符文系表
drop table if exists lol.r_tree;
create table lol.r_tree
(
    id    int          not null comment '主键',
    `key` varchar(32)  not null comment 'KEY',
    name  varchar(32)  not null comment '名称',
    icon  varchar(128) null comment '图标地址',
    primary key (id)
) comment ='符文系表';

-- 符文表
drop table if exists lol.r_rune;
create table lol.r_rune
(
    id         int           not null comment '主键',
    `key`      varchar(32)   not null comment 'KEY',
    tree_id    int           not null comment '所属符文系',
    name       varchar(32)   not null comment '名称',
    icon       varchar(128)  null comment '图标地址',
    short_desc varchar(512)  null comment '简述',
    long_desc  varchar(1024) null comment '详述',
    num_y      int           null comment '位置',
    num_x      int           null comment '位置',
    primary key (id)
) comment ='符文表';

commit;