-- 英雄信息表
drop table if exists lol.c_champion;
create table lol.c_champion
(
    id              int           not null comment '主键',
    `key`           varchar(32)   not null comment '序号',
    name            varchar(32)   not null comment '英雄名',
    title           varchar(32)   not null comment '英雄称号',
    lore            varchar(1024) null comment '背景资料',
    blurb           varchar(1024) null comment '导语',
    tags            varchar(32)   null comment '标签枚举数组',
    partype         varchar(32)   null comment '？？？',
    info_attack     int           null comment '攻击',
    info_defense    int           null comment '防御',
    info_magic      int           null comment '法术',
    info_difficulty int           null comment '难度',
    primary key (id),
    unique (`key`)
) comment ='英雄信息表';

-- 皮肤表
drop table if exists lol.c_skin;
create table lol.c_skin
(
    id          int         not null comment '主键',
    champion_id int         not null comment '英雄ID',
    num         int         not null comment '编号',
    name        varchar(32) not null comment '皮肤名',
    chromas     tinyint     null comment '有无炫彩',
    primary key (id)
) comment = '皮肤表';

-- 英雄统计数据表
drop table if exists lol.c_stats;
create table lol.c_stats
(
    champion_id          int    not null comment '英雄主键',
    hp                   double null comment '生命值',
    hpperlevel           double null comment '成长生命值',
    hpregen              double null comment '生命回复',
    hpregenperlevel      double null comment '成长生命回复',
    mp                   double null comment '法力值',
    mpperlevel           double null comment '成长法力值',
    mpregen              double null comment '法力回复',
    mpregenperlevel      double null comment '成长法力回复',
    attackdamage         double null comment '攻击力',
    attackdamageperlevel double null comment '成长攻击力',
    armor                double null comment '护甲',
    armorperlevel        double null comment '成长护甲',
    spellblock           double null comment '魔法抗性',
    spellblockperlevel   double null comment '成长魔抗',
    attackspeed          double null comment '攻击速度',
    attackspeedperlevel  double null comment '成长攻速',
    attackrange          int    null comment '攻击距离',
    movespeed            int    null comment '移动速度',
    crit                 int    null comment '暴击',
    critperlevel         int    null comment '成长暴击',
    primary key (champion_id)
) comment ='英雄统计数据表';

-- 英雄技能表
drop table if exists lol.c_spell;
create table lol.c_spell
(
    id              int           not null comment '主键，若是英雄技能，设为英雄ID*100+NUM',
    champion_id     int           null comment '英雄ID',
    `key`           varchar(32)   not null comment '名称',
    name            varchar(32)   not null comment '技能名',
    num             tinyint       not null comment '技能类型枚举',
    description     varchar(512)  not null comment '描述',
    maxrank         int           null comment '最高等级',
    tooltip         varchar(1024) null comment '工具提示',
    leveltip_label  varchar(256)  null comment '升级提示标签数组',
    leveltip_effect varchar(1024) null comment '升级数值算式数组',
    cooldown        varchar(32)   null comment '冷却时间数组',
    cooldown_burn   varchar(32)   null comment '冷却时间显示',
    cost            varchar(32)   null comment '法力消耗数组',
    cost_burn       varchar(32)   null comment '法力消耗显示',
    `range`         varchar(32)   null comment '范围数组',
    range_burn      varchar(32)   null comment '范围显示',
    datavalues      varchar(32)   null comment '？？？',
    effect          varchar(256)  null comment '？？？',
    effect_burn     varchar(256)  null comment '？？？',
    vars            varchar(512)  null comment '？？？',
    cost_type       varchar(32)   null comment '？？？',
    maxammo         varchar(32)   null comment '？？？',
    resource        varchar(64)   null comment '？？？',
    summoner_level  int           null comment '最低可用召唤师等级',
    modes           varchar(64)   null comment '适用模式枚举数组',
    primary key (id)
) comment ='英雄技能表';

-- 使用技巧表
drop table if exists lol.c_tip;
create table lol.c_tip
(
    id          int auto_increment not null comment '主键',
    champion_id int                not null comment '英雄ID',
    tip         varchar(512)       not null comment '内容',
    type        tinyint            not null comment '技巧类型枚举',
    primary key (id)
) comment = '使用技巧表';

commit;