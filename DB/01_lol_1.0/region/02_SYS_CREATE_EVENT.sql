-- 玩家事件表
drop table if exists t_event_summoner;
create table t_event_summoner
(
    id          varchar(63) not null comment '召唤师ID',
    status      tinyint     not null comment '状态',
    create_time timestamp   not null comment '创建时间',
    update_time timestamp   null comment '更新时间',
    primary key (id),
    index (status)
) comment ='玩家事件表';

-- 对局事件表
drop table if exists t_event_match;
create table t_event_match
(
    id          varchar(16) not null comment '对局主键',
    status      tinyint     not null comment '状态',
    create_time timestamp   not null comment '创建时间',
    update_time timestamp   null comment '更新时间',
    primary key (id),
    index (status)
) comment ='对局事件表';

commit;