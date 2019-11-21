-- 系统配置表
drop table if exists lol.t_config;
create table lol.t_config
(
    name        varchar(32)   not null comment '配置名',
    value       varchar(1024) not null comment '配置值',
    description varchar(512)  not null comment '描述',
    primary key (name)
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
    primary key (id)
) comment ='图片资源表';


-- 事件表
drop table if exists lol.t_event;
create table lol.t_event
(
    id      int auto_increment not null comment '主键',
    type    tinyint            not null comment '类型，对应处理方式',
    context varchar(64)        not null comment '上下文',
    status  tinyint            not null comment '状态',
    primary key (id)
) comment ='事件表';

commit;