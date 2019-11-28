-- 召唤师信息表
drop table if exists lol.s_summoner;
create table lol.s_summoner
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
    last_match      timestamp   not null comment '最后游戏时间',
    primary key (id),
    unique (account_id),
    unique (puuid),
    index (name)
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
    primary key (summoner_id, champion_id)
) comment ='英雄成就表';

-- 个人排位信息表
drop table if exists lol.s_league;
create table lol.s_league
(
    summoner_id     varchar(63) not null comment '召唤师ID',
    queue_type      tinyint     not null comment '排位队列',
    league_id       varchar(64) null comment '战区ID',
    tier            tinyint     null comment '段位',
    `rank`          tinyint     null comment '小段位',
    summoner_name   varchar(32) null comment '召唤师名称',
    hot_streak      tinyint     null comment '是否连胜',
    wins            int         null comment '胜场',
    veteran         tinyint     null comment '是否老兵',
    losses          int         null comment '负场',
    inactive        tinyint     null comment '？？？',
    fresh_blood     tinyint     null comment '是否新兵',
    league_points   int         null comment '胜点',
    series_target   int         null comment '晋级赛目标胜场',
    series_wins     int         null comment '晋级赛胜场',
    series_losses   int         null comment '晋级赛负场',
    series_progress varchar(32) null comment '？？？',
    primary key (summoner_id, queue_type)
) comment ='个人排位信息表';

commit;