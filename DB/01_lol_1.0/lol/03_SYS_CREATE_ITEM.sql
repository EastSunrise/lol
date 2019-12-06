-- 装备表
drop table if exists lol.i_item;
create table lol.i_item
(
    id                int           not null comment '主键',
    name              varchar(32)   not null comment '名称',
    description       varchar(1024) not null comment '描述',
    colloq            varchar(128)  not null comment '？？？',
    plaintext         varchar(64)   null comment '概述',
    `from`            varchar(128) null comment '子物品主键数组，逗号分隔',
    `into`            varchar(128) null comment '合成的物品主键数组，逗号分隔',
    gold_base         int          null comment '合成价格',
    gold_purchasable  tinyint      null comment '是否可购买',
    gold_total        int          null comment '总价格',
    gold_sell         int          null comment '售价',
    tags              varchar(64)  null comment '标签枚举数组',
    maps              varchar(32)  null comment '可用地图枚举数组',
    depth             int          null comment '合成树深度',
    effect            varchar(512) null comment '？？？',
    consumed          tinyint      null comment '？？？',
    consume_on_full   tinyint      null comment '？？？',
    in_store          tinyint      null comment '？？？',
    hide_from_all     tinyint      null comment '？？？',
    stacks            int          null comment '？？？',
    required_champion varchar(32)  null comment '可购买的英雄',
    special_recipe    int          null comment '？？？',
    required_ally     varchar(32)  null comment '友方英雄要求，目前仅有Ornn',
    primary key (id)
) comment ='装备表';


-- 装备统计数据表
drop table if exists lol.i_stats;
create table lol.i_stats
(
    item_id                    int    not null comment '主键',
    flat_hp_pool_mod           int    null comment '？？？',
    flat_mp_pool_mod           int    null comment '？？？',
    flat_hp_regen_mod          double null comment '？？？',
    flat_armor_mod             int    null comment '？？？',
    flat_physical_damage_mod   int    null comment '？？？',
    flat_magic_damage_mod      int    null comment '？？？',
    flat_movement_speed_mod    int    null comment '？？？',
    percent_movement_speed_mod double null comment '？？？',
    percent_attack_speed_mod   double null comment '？？？',
    flat_crit_chance_mod       double null comment '？？？',
    flat_spell_block_mod       int    null comment '？？？',
    percent_life_steal_mod     double null comment '？？？',
    primary key (item_id)
) comment ='装备统计数据表';

-- 装备推荐表
drop table if exists lol.ct_recommended;
create table lol.ct_recommended
(
    id                              int         not null comment '主键，手动生成',
    champion                        varchar(32) not null comment '英雄KEY',
    title                           varchar(32) not null comment '标题',
    map                             tinyint     null comment '适用地图枚举',
    mode                            tinyint     null comment '适用模式枚举',
    type                            tinyint     null comment '类型枚举',
    priority                        tinyint     null comment '？？？',
    custom_tag                      varchar(32) null comment '自定义标签',
    sortrank                        int         null comment '？？？',
    extension_page                  tinyint     null comment '？？？',
    use_obvious_checkmark           tinyint     null comment '？？？',
    exten_ornn_page                 tinyint     null comment '？？？',
    required_perk                   varchar(32) null comment '？？？',
    custom_panel                    varchar(32) null comment '？？？',
    custom_panel_buff_currency_name varchar(32) null comment '？？？',
    custom_panel_currency_type      varchar(32) null comment '？？？',
    primary key (id)
) comment ='装备推荐表';

-- 装备推荐分区表
drop table if exists lol.ct_block;
create table lol.ct_block
(
    id                     int auto_increment not null comment '主键',
    recommended_id         int                not null comment '所属推荐主键',
    type                   tinyint            null comment '类型枚举',
    rec_math               tinyint            null comment '？？？',
    rec_steps              tinyint            null comment '？？？',
    min_summoner_level     int                null comment '最低召唤师等级',
    max_summoner_level     int                null comment '最高召唤师等级',
    show_if_summoner_spell varchar(32)        null comment '适用召唤师技能',
    hide_if_summoner_spell varchar(32)        null comment '不适用召唤师技能',
    append_after_section   varchar(32)        null comment '？？？',
    visible_with_all_of    varchar(32)        null comment '？？？',
    hidden_with_any_of     varchar(32)        null comment '？？？',
    items                  varchar(1024)      null comment '推荐的装备数组',
    primary key (id)
) comment ='装备推荐分区表';

commit;