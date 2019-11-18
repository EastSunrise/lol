-- 战区信息表
drop table if exists lol.l_league;
create table lol.l_league
(
    league_id varchar(64) not null comment '主键',
    name      varchar(32) not null comment '战区名称',
    tier      tinyint     not null comment '段位',
    queue     tinyint     not null comment '排位队列',
    constraint l_league_pk primary key (league_id)
) comment ='战区信息表';

-- 个人排位信息表
drop table if exists lol.l_entry;
create table lol.l_entry
(
    id            int         not null comment '主键',
    summoner_id   varchar(63) not null comment '召唤师ID',
    queue         tinyint     not null comment '排位队列，目前仅限单双排',
    position      tinyint     not null comment '位置，中上射野辅',
    summoner_name varchar(32) null comment '召唤师名称',
    league_id     varchar(64) null comment '战区ID',
    league_name   varchar(32) null comment '战区名称',
    tier          tinyint     null comment '段位，目前不支持顶尖段位',
    `rank`        tinyint     null comment '小段位',
    league_points int         null comment '胜点',
    wins          int         null comment '胜场',
    losses        int         null comment '负场',
    veteran       tinyint     null comment '是否老兵',
    inactive      tinyint     null comment '？？？',
    fresh_blood   tinyint     null comment '是否新兵',
    hot_streak    tinyint     null comment '是否连胜',
    series_target int         null comment '晋级赛目标胜场',
    series_wins   int         null comment '晋级赛胜场',
    series_losses int         null comment '晋级赛负场',
    constraint l_entry_pk primary key (id)
) comment ='个人排位信息表';

commit;