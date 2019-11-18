-- 物品信息表
drop table if exists lol.i_item;
create table lol.i_item
(
    id               int           not null comment '主键',
    name             varchar(32)   not null comment '名称',
    description      varchar(1024) not null comment '描述',
    colloq           varchar(128)  not null comment '',
    plaintext        varchar(64)   null comment '概述',
    `from`           varchar(128)  null comment '子物品主键数组，逗号分隔',
    `into`           varchar(128)  null comment '合成的物品主键数组，逗号分隔',
    gold_base        int           null comment '合成价格',
    gold_purchasable tinyint       null comment '是否可购买',
    gold_total       int           null comment '总价格',
    gold_sell        int           null comment '售价',
    tags             varchar(64)   null comment '标签枚举数组. Ref. ItemTagEnum',
    maps             varchar(32)   null comment '可用地图枚举数组. Ref. MapEnum',
    depth            int           null comment '合成树深度',
    constraint i_item_pk primary key (id)
) comment ='物品信息表';


-- 物品属性表
drop table if exists lol.i_stats;
create table lol.i_stats
(
    item_id int not null comment '主键',
    constraint i_stats_pk primary key (item_id)
) comment ='物品属性表';

commit;