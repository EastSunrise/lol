-- 玩家事件表
drop table if exists t_event_summoner;
create table t_event_summoner
(
    id     varchar(63) not null comment '主键',
    status tinyint     not null comment '状态',
    primary key (id),
    index (status)
) comment ='玩家事件表';

-- 对局事件表
drop table if exists t_event_match;
create table t_event_match
(
    id     varchar(16) not null comment '主键',
    status tinyint     not null comment '状态',
    primary key (id),
    index (status)
) comment ='对局事件表';

commit;