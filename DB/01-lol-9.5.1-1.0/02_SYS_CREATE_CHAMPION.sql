-- 英雄表
drop table if exists lol.c_champion;
create table lol.c_champion
(
    id              varchar(32) not null comment '主键，同英文名',
    'key'           int         not null comment '序号',
    name            varchar(32) not null comment '英雄名',
    title           varchar(32) not null comment '英雄称号',
    lore            varchar(1024) comment '简介',
    blurb           varchar(1024) comment '导语',
    tags            varchar(32) comment '标签code数组，以逗号分隔',
    partype         varchar(32) comment '???',
    info_attack     int comment '物理',
    info_defense    int comment '防御',
    info_magic      int comment '魔法',
    info_difficulty int comment '难度',
    constraint c_champion_pk primary key (id)
) comment ='英雄信息表';

-- 英雄属性表
drop table if exists lol.c_stats;
create table lol.c_stats
(
    id                   varchar(32) not null comment '英雄主键',
    hp                   double      null comment '基础生命值',
    hpperlevel           double      null comment '成长生命值',
    hpregen              double      null comment '生命回复',
    hpregenperlevel      double      null comment '成长生命回复',
    mp                   double      null comment '法力值',
    mpperlevel           double      null comment '成长法力值',
    mpregen              double      null comment '法力回复',
    mpregenperlevel      double      null comment '成长法力回复',
    attackdamage         double      null comment '攻击力',
    attackdamageperlevel double      null comment '成长攻击力',
    armor                double      null comment '护甲',
    armorperlevel        double      null comment '成长护甲',
    spellblock           double      null comment '魔抗',
    spellblockperlevel   double      null comment '成长魔抗',
    attackspeed          double      null comment '攻击速度',
    attackspeedperlevel  double      null comment '成长攻速',
    attackrange          int         null comment '攻击距离',
    movespeed            int         null comment '移速',
    crit                 int         null comment '暴击',
    critperlevel         int         null comment '成长暴击',
    constraint c_stats_pk primary key (id)
) comment ='英雄属性表';

-- 英雄技能表
drop table if exists lol.c_spell;
create table lol.c_spell
(
    id              varchar(32)   not null comment '主键',
    champion_id     varchar(32)   not null comment '英雄ID',
    name            varchar(32)   not null comment '技能名',
    num             tinyint       not null comment '0.passive 1.Q 2.W 3.E 4.R',
    description     varchar(512)  not null comment '描述',
    maxrank         int           not null comment '最高等级',
    level_tips      varchar(512)  null comment '等级提示',
    tooltip         varchar(1024) null comment '工具提示',
    leveltip_label  varchar(256)  null comment '升级提示标签数组，逗号分隔',
    leveltip_effect varchar(1024) null comment '升级数值算式数组，逗号分隔',
    cooldown        varchar(32)   null comment '冷却时间数组，逗号分隔',
    cooldown_burn   varchar(32)   null comment '冷却时间显示',
    cost            varchar(32)   null comment '法力消耗数组，逗号分隔',
    cost_burn       varchar(32)   null comment '法力消耗显示',
    `range`         varchar(32)   null comment '范围数组，逗号分隔',
    range_burn      varchar(32)   null comment '范围显示',
    datavalues      varchar(256)  null comment '',
    effect          varchar(256)  null comment '',
    effect_burn     varchar(256)  null comment '',
    vars            varchar(256)  null comment '',
    cost_type       varchar(32)   null comment '？？？',
    maxammo         varchar(32)   null comment '？？？',
    resource        varchar(32)   null comment '？？？',
    constraint c_spell_pk primary key (id)
) comment ='英雄技能表';


-- 英雄技巧表
drop table if exists lol.c_tip;
create table lol.c_tip
(
    id          int auto_increment not null comment '主键',
    champion_id varchar(32)        not null comment '英雄ID',
    tip         varchar(512)       not null comment '内容',
    type        tinyint            not null comment '0-使用，1-对抗',
    constraint c_tip_pk primary key (id)
) comment = '英雄技巧表';


-- 皮肤表
drop table if exists lol.c_skin;
create table lol.c_skin
(
    id          varchar(32) not null comment '主键',
    champion_id varchar(32) not null comment '英雄id',
    num         int         not null comment '编号',
    name        varchar(32) not null comment '皮肤名称',
    chromas     tinyint comment '有无炫彩',
    constraint c_skin_pk primary key (id)
) comment = '皮肤信息表';

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
    constraint s_mastery_pk primary key (summoner_id)
) comment ='英雄成就表';

commit;