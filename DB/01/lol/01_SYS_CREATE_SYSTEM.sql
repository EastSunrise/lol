-- 系统配置表
drop table if exists lol.t_config;
create table lol.t_config
(
    name        varchar(32)   not null comment '配置名',
    value       varchar(1024) not null comment '配置值',
    description varchar(512)  not null comment '描述',
    create_time timestamp     not null comment '创建时间',
    update_time timestamp     null comment '更新时间',
    primary key (name)
) comment ='系统配置表';

-- 图片资源表
drop table if exists lol.t_image;
create table lol.t_image
(
    id         int auto_increment not null comment '主键',
    related_id int                not null comment '对象ID',
    `group`    tinyint            not null comment '分组枚举',
    full       varchar(64)        not null comment '完整图片名',
    sprite     varchar(64)        not null comment '缩略图名',
    x          int                null comment '横坐标',
    y          int                null comment '纵坐标',
    w          int                null comment '宽',
    h          int                null comment '高',
    primary key (id)
) comment ='图片资源表';

commit;