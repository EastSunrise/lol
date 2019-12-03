-- 对局信息表
drop table if exists m_match;
create table m_match
(
    game_id        bigint      not null comment '主键',
    platform_id    tinyint     null comment '服务器',
    game_creation  timestamp   not null comment '开始时间',
    game_duration  int         null comment '时长，单位：s',
    map_id         tinyint     null comment '地图',
    season_id      tinyint     null comment '赛季',
    game_version   varchar(32) null comment '游戏版本',
    game_mode      tinyint     null comment '游戏模式',
    game_type      tinyint     null comment '游戏类型',
    frame_interval int         null comment '事件计算间隔，单位：ms',
    primary key (game_id)
) comment ='对局信息表';

-- 对局时间帧表
drop table if exists m_match_frame;
create table m_match_frame
(
    id                        bigint auto_increment not null comment '主键',
    game_id                   bigint                not null comment '对局ID',
    timeline                  int                   not null comment '时间线',
    timing                    int                   not null comment '时间点',
    type                      tinyint               not null comment '对局事件类型',
    participant_id            int                   null comment '玩家序号',
    skill_slot                int                   null comment '？？？',
    level_up_type             tinyint               null comment '升级类型',
    item_id                   int                   null comment '物品ID',
    after_id                  int                   null comment '？？？',
    before_id                 int                   null comment '？？？',
    ward_type                 tinyint               null comment '视野类型',
    creator_id                int                   null comment '？？？',
    position_x                int                   null comment '坐标X',
    position_y                int                   null comment '坐标Y',
    killer_id                 int                   null comment '击杀者ID',
    victim_id                 int                   null comment '被杀者ID',
    assisting_participant_ids varchar(32)           null comment '助攻者ID',
    monster_type              tinyint               null comment '野怪类型',
    monster_sub_type          tinyint               null comment '野怪子类型',
    team_id                   tinyint               null comment '队伍ID',
    building_type             tinyint               null comment '重生类型',
    lane_type                 tinyint               null comment '分类类型',
    tower_type                tinyint               null comment '建筑物类型',
    constraint m_match_frame_pk primary key (id)
) comment ='对局时间帧表';

-- 对局队伍数据表
drop table if exists m_team_stats;
create table m_team_stats
(
    game_id                bigint       not null comment '主键',
    team_id                tinyint      not null comment '队伍ID',
    win                    tinyint      not null comment '队伍对局结果',
    first_blood            tinyint      null comment '是否一血',
    first_tower            tinyint      null comment '是否一塔',
    first_inhibitor        tinyint      null comment '是否第一个水晶',
    first_baron            tinyint     null comment '是否第一条男爵',
    first_dragon           tinyint      null comment '是否第一条龙',
    first_rift_herald      tinyint      null comment '是否第一个峡谷先锋',
    tower_kills            int          null comment '推塔数',
    inhibitor_kills        int          null comment '水晶数',
    baron_kills            int          null comment '男爵击杀数',
    dragon_kills           int          null comment '小龙击杀数',
    vilemaw_kills          int          null comment '卑鄙之喉击杀数',
    rift_herald_kills      int          null comment '先锋击杀数',
    dominion_victory_score int          null comment '？？？',
    bans                   varchar(256) null comment '英雄禁用',
    primary key (game_id, team_id)
) comment ='对局队伍数据表';

-- 对局玩家表
drop table if exists m_participant;
create table m_participant
(
    id                  bigint      not null comment '主键',
    game_id             bigint      not null comment '对局ID',
    participant_id      int         not null comment '玩家序号',
    platform_id         tinyint     not null comment '服务器',
    summoner_id         varchar(63) not null comment '召唤师ID',
    account_id          varchar(56) not null comment '账户ID',
    current_platform_id tinyint     not null comment '当前服务器',
    current_account_id  varchar(56) not null comment '当前账户ID',
    match_history_uri   varchar(64) null comment '对局记录URI',
    profile_icon        int         null comment '图标ID',
    team_id             tinyint     not null comment '所属队伍',
    champion_id         int         not null comment '使用的英雄ID',
    spell1_id           int         null comment '召唤师技能ID',
    spell2_id           int         null comment '召唤师技能ID',
    role                tinyint     not null comment '角色',
    lane                tinyint     not null comment '分路',
    primary key (id)
) comment ='对局玩家表';

-- 对局玩家数据表
drop table if exists m_participant_stats;
create table m_participant_stats
(
    id                        bigint  not null comment '主键',
    win                       tinyint null comment '是否胜利',
    item0                     int     null comment '装备0',
    item1                     int     null comment '装备1',
    item2                     int     null comment '装备2',
    item3                     int     null comment '装备3',
    item4                     int     null comment '装备4',
    item5                     int     null comment '装备5',
    item6                     int     null comment '装备6',
    kills                     int     null comment '击杀数',
    deaths                    int     null comment '死亡数',
    assists                   int     null comment '助攻数',
    largest_killing_spree     int     null comment '最大连杀',
    largest_multi_kill        int     null comment '最大多杀',
    killing_sprees            int     null comment '连杀数',
    longest_time_spent_living int     null comment '最长存活时长',
    double_kills              int     null comment '双杀数',
    triple_kills                        int     null comment '三杀数',
    quadra_kills                        int     null comment '四杀数',
    penta_kills                         int     null comment '五杀数',
    unreal_kills                        int     null comment '？？？',
    total_damage_dealt                  bigint  null comment '总伤害',
    magic_damage_dealt                  bigint  null comment '魔法伤害',
    physical_damage_dealt               bigint  null comment '物理伤害',
    true_damage_dealt                   bigint  null comment '真实伤害',
    largest_critical_strike             int     null comment '？？？',
    total_damage_dealt_to_champions     bigint  null comment '对英雄的伤害',
    magic_damage_dealt_to_champions     bigint  null comment '对英雄的魔法伤害',
    physical_damage_dealt_to_champions  bigint  null comment '对英雄的物理伤害',
    true_damage_dealt_to_champions      bigint  null comment '对英雄的真实伤害',
    total_heal                          bigint  null comment '总治疗',
    total_units_healed                  int     null comment '治疗单位数',
    damage_self_mitigated               bigint  null comment '自我缓和伤害',
    damage_dealt_to_objectives          bigint  null comment '对中立生物伤害',
    damage_dealt_to_turrets             bigint  null comment '对塔的伤害',
    vision_score                        bigint  null comment '视野得分',
    vision_wards_bought_in_game         int     null comment '购买守卫',
    sight_wards_bought_in_game          int     null comment '购买守卫',
    wards_placed                        int     null comment '插眼数',
    wards_killed                        int     null comment '排眼数',
    total_damage_taken                  bigint  null comment '承受伤害',
    magical_damage_taken                bigint  null comment '承受魔法伤害',
    physical_damage_taken               bigint  null comment '承受物理伤害',
    true_damage_taken                   bigint  null comment '承受真实伤害',
    gold_earned                         int     null comment '获得金钱',
    gold_spent                          int     null comment '花费金钱',
    turret_kills                        int     null comment '推塔数',
    inhibitor_kills                     int     null comment '水晶数',
    total_minions_killed                int     null comment '补刀数',
    neutral_minions_killed              int     null comment '补兵数',
    neutral_minions_killed_team_jungle  int     null comment '击杀己方野怪',
    neutral_minions_killed_enemy_jungle int     null comment '击杀敌方野怪',
    total_time_crowd_control_dealt      int     null comment '控制时长',
    champ_level                         int     null comment '英雄等级',
    time_c_cing_others                  bigint  null comment '？？？',
    first_blood_kill                    tinyint null comment '是否一血',
    first_blood_assist                  tinyint null comment '是否第一个助攻',
    first_tower_kill          tinyint null comment '是否一塔',
    first_tower_assist        tinyint null comment '是否一塔助攻',
    first_inhibitor_kill      tinyint null comment '是否第一个水晶',
    first_inhibitor_assist    tinyint null comment '是否第一个水晶助攻',
    combat_player_score       int     null comment '战斗得分',
    objective_player_score    int     null comment '资源控制得分',
    total_player_score        int     null comment '总得分',
    total_score_rank          int     null comment '？？？',
    player_score0             int     null comment '？？？',
    player_score1             int     null comment '？？？',
    player_score2             int     null comment '？？？',
    player_score3             int     null comment '？？？',
    player_score4             int     null comment '？？？',
    player_score5             int     null comment '？？？',
    player_score6             int     null comment '？？？',
    player_score7             int     null comment '？？？',
    player_score8             int     null comment '？？？',
    player_score9             int     null comment '？？？',
    perk0                     int     null comment '？？？',
    perk0_var1                int     null comment '？？？',
    perk0_var2                int     null comment '？？？',
    perk0_var3                int     null comment '？？？',
    perk1                     int     null comment '？？？',
    perk1_var1                int     null comment '？？？',
    perk1_var2                int     null comment '？？？',
    perk1_var3                int     null comment '？？？',
    perk2                     int     null comment '？？？',
    perk2_var1                int     null comment '？？？',
    perk2_var2                int     null comment '？？？',
    perk2_var3                int     null comment '？？？',
    perk3                     int     null comment '？？？',
    perk3_var1                int     null comment '？？？',
    perk3_var2                int     null comment '？？？',
    perk3_var3                int     null comment '？？？',
    perk4                     int     null comment '？？？',
    perk4_var1                int     null comment '？？？',
    perk4_var2                int     null comment '？？？',
    perk4_var3                int     null comment '？？？',
    perk5                     int     null comment '？？？',
    perk5_var1                int     null comment '？？？',
    perk5_var2                int     null comment '？？？',
    perk5_var3                int     null comment '？？？',
    perk_primary_style        int     null comment '？？？',
    perk_sub_style            int     null comment '？？？',
    stat_perk0                int     null comment '？？？',
    stat_perk1                int     null comment '？？？',
    stat_perk2                int     null comment '？？？',
    primary key (id)
) comment ='对局玩家数据表';

-- 对局玩家时间帧表
drop table if exists m_participant_frame;
create table m_participant_frame
(
    id                    bigint auto_increment not null comment '主键',
    related_id            bigint                not null comment '关联玩家的对局内ID',
    timeline              int                   not null comment '时间线',
    position_x            int                   null comment '坐标X',
    position_y            int                   null comment '坐标Y',
    current_gold          int                   null comment '当前金币',
    total_gold            int                   null comment '总金币',
    level                 int                   null comment '等级',
    xp                    int                   null comment '经验',
    minions_killed        int                   null comment '补刀数',
    jungle_minions_killed int                   null comment '刷野数',
    dominion_score        int                   null comment '自我得分',
    team_score            int                   null comment '团队得分',
    constraint m_participant_frame_pk primary key (id)
) comment ='对局玩家时间帧表';

commit;