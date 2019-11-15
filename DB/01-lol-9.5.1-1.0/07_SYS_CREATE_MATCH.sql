-- 对局信息表
drop table if exists lol.m_match;
create table lol.m_match
(
    game_id        bigint      not null comment '主键',
    game_creation  timestamp   not null comment '开始时间',
    game_duration  int         null comment '时长，单位：s',
    game_version   varchar(32) null comment '游戏版本',
    game_mode      tinyint     null comment '游戏模式',
    game_type      tinyint     null comment '游戏类型',
    season_id      tinyint     null comment '赛季',
    queue_id       tinyint     null comment '游戏队列',
    platform_id    tinyint     null comment '平台',
    map_id         tinyint     null comment '地图',
    frame_interval int         null comment '事件计算间隔，单位：ms',
    constraint m_match_pk primary key (game_id)
) comment ='对局信息表';


-- 对局人员表
drop table if exists lol.m_participant;
create table lol.m_participant
(
    id                           bigint       not null comment '主键',
    game_id                      bigint       not null comment '比赛ID',
    participant_id               int          not null comment '参与者ID',
    summoner_id                  varchar(63)  not null comment '召唤师ID',
    champion_id                  int          null comment '使用的英雄ID',
    team_id                      tinyint      null comment '100 for blue side. 200 for red side.',
    lane                         tinyint      null comment '分路',
    role                         tinyint      null comment '角色',
    spell1_id                    varchar(32)  null comment '召唤师技能ID',
    spell2_id                    varchar(32)  null comment '召唤师技能ID',
    highest_achieved_season_tier tinyint      null comment '赛季最高段位',
    timeline_                    varchar(256) null comment '',
    stat_                        varchar(256) null comment '基本数据',
    constraint m_participant_pk primary key (id)
) comment ='对局人员表';

commit;