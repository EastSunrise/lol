-- 召唤师信息表
drop table if exists lol.s_summoner;
create table s_summoner
(
    id              varchar(63) not null comment '主键，Encrypted',
    account_id      varchar(56) not null comment 'Encrypted',
    puuid           char(78)    not null comment 'Encrypted',
    name            varchar(32) not null comment '召唤师昵称',
    profile_icon_id int         null comment '头像ID',
    revision_date   timestamp   null comment '修改时间',
    summoner_level  int         null comment '召唤师等级',
    score           int         null comment '总成就点数',
    last_update     timestamp   not null comment '最近更新时间',
    primary key (id)
) comment ='召唤师信息表';

-- 英雄成就表
drop table if exists lol.s_mastery;
create table lol.s_mastery
(
    summoner_id                      varchar(63) not null comment 'Encrypted',
    champion_id                      int         not null comment '英雄ID',
    chest_granted                    tinyint     null comment '是否已获取赛季宝箱',
    champion_level                   int         not null comment '英雄等级',
    champion_points                  int         not null comment '英雄成就点数',
    champion_points_until_next_level int         null comment '升至下一级所需成就点数，最高级是为0',
    champion_points_since_last_level int         null comment '当前等级已获取成就点数',
    tokens_earned                    int         null comment '？？？',
    last_play_time                   timestamp   null comment '最近一次使用时间',
    constraint s_mastery_pk primary key (summoner_id, champion_id)
) comment ='英雄成就表';

commit;