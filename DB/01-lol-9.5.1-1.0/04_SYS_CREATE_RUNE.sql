-- 符文信息表
drop table if exists lol.r_rune;
create table lol.r_rune
(
    id         int           not null comment '主键',
    `key`      varchar(32)   not null comment 'KEY',
    name       varchar(32)   not null comment '名称',
    icon       varchar(128)  null comment '图标地址',
    short_desc varchar(512)  null comment '简述',
    long_desc  varchar(1024) null comment '详述',
    series_id  int           not null comment '所属系列',
    num_y      int           null comment '位置',
    num_x      int           null comment '位置',
    constraint r_rune_pk primary key (id)
) comment ='符文信息表';


-- 符文系信息表
drop table if exists lol.r_tree;
create table lol.r_tree
(
    id    int          not null comment '主键',
    `key` varchar(32)  not null comment 'KEY',
    name  varchar(32)  not null comment '名称',
    icon  varchar(128) null comment '图标地址',
    constraint r_series_pk primary key (id)
) comment ='符文系信息表';

commit;