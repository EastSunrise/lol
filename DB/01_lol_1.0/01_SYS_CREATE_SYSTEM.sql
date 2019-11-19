-- 系统配置表
drop table if exists lol.t_config;
create table lol.t_config
(
    name        varchar(32)  not null comment '配置名',
    value       varchar(512) not null comment '配置值',
    description varchar(512) not null comment '描述',
    constraint t_config_pk primary key (name)
) comment ='系统配置表';

-- 图片资源表
drop table if exists lol.t_image;
create table lol.t_image
(
    id         int auto_increment not null comment '主键',
    related_id int                not null comment '对象ID',
    `group`    tinyint            not null comment '分组枚举',
    full       varchar(32)        not null comment '完整图片名',
    sprite     varchar(32)        not null comment '缩略图名',
    x          int                null comment '横坐标',
    y          int                null comment '纵坐标',
    w          int                null comment '宽',
    h          int                null comment '高',
    constraint t_image_pk primary key (id)
) comment ='图片资源表';


-- 事件表
drop table if exists lol.t_event;
create table lol.t_event
(
    id   int auto_increment not null comment '主键',
    name varchar(32)        not null comment '召唤师名称',
    constraint t_event_pk primary key (id)
) comment ='事件表';

commit;