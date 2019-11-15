-- 召唤师信息表
CREATE TABLE s_summoner
(
    ID                VARCHAR(63) COMMENT '主键，Encrypted',
    ACCOUNT_ID        VARCHAR(56) COMMENT 'Encrypted',
    PUUID             CHAR(78) COMMENT 'Encrypted',
    NAME              VARCHAR(32) COMMENT '召唤师昵称',
    PROFILE_ICON_ID   INT COMMENT '头像ID',
    REVISION_DATE     TIMESTAMP COMMENT '修改时间',
    SUMMONER_LEVEL    INT COMMENT '召唤师等级',
    LAST_CHECKED_TIME TIMESTAMP DEFAULT '2019-01-01 00:00:00' COMMENT '最近一次用来扩展数据的时间，默认2019.1.1',
    CONSTRAINT S_SUMMONER_PK PRIMARY KEY (ID)
) comment ='召唤师信息表';


-- 召唤师技能表
drop table if exists lol.s_spell;
create table lol.s_spell
(
    id             varchar(32)   not null comment '主键',
    'key'          varchar(32)   not null comment 'KEY',
    name           varchar(32)   not null comment '技能名',
    description    varchar(512)  null comment '描述',
    tooltip        varchar(1024) null comment '工具提示',
    maxrank        int           null comment '最高等级',
    cooldown       varchar(32)   null comment '',
    mode           varchar(32)   null comment '适用模式code数组，逗号分隔',
    summoner_level int           null comment '可用等级，仅限召唤师技能',
    constraint s_spell_pk primary key (id)
) comment ='召唤师技能表';


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