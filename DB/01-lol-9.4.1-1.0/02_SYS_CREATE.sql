-- 英雄 Champion
CREATE TABLE c_champion
(
  ID                         INT COMMENT '主键',
  NAME                       VARCHAR(32) COMMENT '英雄名',
  TITLE                      VARCHAR(32) COMMENT '英雄称号',
  IMAGE_FULL                 VARCHAR(32) COMMENT '英雄图片名',
  IMAGE_SPRITE               VARCHAR(32) COMMENT '缩略图名',
  TAGS                       VARCHAR(32) COMMENT '标签',
  LORE                       VARCHAR(1024) COMMENT '简介',
  BLURB                      VARCHAR(1024) COMMENT '导语',
  PARTYPE                    VARCHAR(32) COMMENT '？？？',
  INFO_ATTACK                INT COMMENT '物理',
  INFO_DEFENSE               INT COMMENT '防御',
  INFO_MAGIC                 INT COMMENT '法术',
  INFO_DIFFICULTY            INT COMMENT '难度',
  STATS_HP                   DOUBLE COMMENT '基础生命值',
  STATS_HPPERLEVEL           DOUBLE COMMENT '成长生命值',
  STATS_HPREGEN              DOUBLE COMMENT '生命回复',
  STATS_HPREGENPERLEVEL      DOUBLE COMMENT '成长生命回复',
  STATS_MP                   DOUBLE COMMENT '法力值',
  STATS_MPPERLEVEL           DOUBLE COMMENT '成长法力值',
  STATS_MPREGEN              DOUBLE COMMENT '法力回复',
  STATS_MPREGENPERLEVEL      DOUBLE COMMENT '成长法力回复',
  STATS_ATTACKDAMAGE         DOUBLE COMMENT '攻击力',
  STATS_ATTACKDAMAGEPERLEVEL DOUBLE COMMENT '成长攻击力',
  STATS_ARMOR                DOUBLE COMMENT '护甲',
  STATS_ARMORPERLEVEL        DOUBLE COMMENT '成长护甲',
  STATS_SPELLBLOCK           DOUBLE COMMENT '魔抗',
  STATS_SPELLBLOCKPERLEVEL   DOUBLE COMMENT '成长魔抗',
  STATS_ATTACKSPEED          DOUBLE COMMENT '攻击速度',
  STATS_ATTACKSPEEDPERLEVEL  DOUBLE COMMENT '成长攻速',
  STATS_ATTACKRANGE          INT COMMENT '攻击距离',
  STATS_MOVESPEED            INT COMMENT '移速',
  STATS_CRIT                 INT COMMENT '暴击',
  STATS_CRITPERLEVEL         INT COMMENT '成长暴击',
  CONSTRAINT C_CHAMPION_PK PRIMARY KEY (ID)
);

-- 英雄技能
CREATE TABLE c_spell
(
  ID             VARCHAR(32) COMMENT '主键',
  CHAMPION_ID    INT COMMENT '英雄ID，仅限英雄技能',
  SPELL_TYPE     TINYINT COMMENT '技能类型，英雄技能，召唤师技能',
  NAME           VARCHAR(32) COMMENT '技能名',
  MODES          VARCHAR(64) COMMENT '适用模式，仅限召唤师技能',
  SUMMONER_LEVEL INT COMMENT '可用等级，仅限召唤师技能',
  DESCRIPTION    VARCHAR(512) COMMENT '描述',
  LEVEL_TIPS     VARCHAR(512) COMMENT '等级提示，仅限英雄技能',
  TOOLTIP        VARCHAR(1024) COMMENT '工具提示',
  IMAGE_FULL     VARCHAR(64) COMMENT '技能图片名',
  IMAGE_SPRITE   VARCHAR(32) COMMENT '缩略图名',
  -- wsg
  CONSTRAINT C_SPELL_PK PRIMARY KEY (ID),
  CONSTRAINT C_SPELL_FK FOREIGN KEY (CHAMPION_ID) REFERENCES c_champion (ID)
);

-- 英雄使用技巧
CREATE TABLE c_tip
(
  ID          INT AUTO_INCREMENT COMMENT '主键',
  CHAMPION_ID INT COMMENT '英雄ID',
  TIP         VARCHAR(512) COMMENT '内容',
  TYPE        TINYINT(1) COMMENT '0-使用，1-对抗',
  CONSTRAINT C_TIP_PK PRIMARY KEY (ID),
  CONSTRAINT C_TIP_FK FOREIGN KEY (CHAMPION_ID) REFERENCES c_champion (ID)
);

-- 英雄皮肤
CREATE TABLE c_skin
(
  ID          VARCHAR(32) COMMENT '主键',
  CHAMPION_ID INT COMMENT '英雄ID',
  NUM         INT COMMENT '编号',
  NAME        VARCHAR(32) COMMENT '皮肤名',
  CHROMAS     TINYINT(1) COMMENT '有无炫彩',
  CONSTRAINT C_SKIN_PK PRIMARY KEY (ID),
  CONSTRAINT C_SKIN_FK FOREIGN KEY (CHAMPION_ID) REFERENCES c_champion (ID)
);

-- 英雄成就
CREATE TABLE c_mastery
(
  ID                               INT AUTO_INCREMENT COMMENT '主键',
  SUMMONER_ID                      VARCHAR(63) COMMENT 'Encrypted',
  CHAMPION_ID                      INT COMMENT '英雄ID',
  CHEST_GRANTED                    TINYINT(1) COMMENT '是否已获取赛季宝箱',
  CHAMPION_LEVEL                   INT COMMENT '英雄等级',
  CHAMPION_POINTS                  INT COMMENT '英雄成就点数',
  CHAMPION_POINTS_UNTIL_NEXT_LEVEL INT COMMENT '升至下一级所需成就点数，最高级是为0',
  CHAMPION_POINTS_SINCE_LAST_LEVEL INT COMMENT '当前等级已获取成就点数',
  TOKENS_EARNED                    INT COMMENT '？？？',
  LAST_PLAY_TIME                   TIMESTAMP COMMENT '最近一次使用时间',
  CONSTRAINT C_MASTERY_PK PRIMARY KEY (ID),
  CONSTRAINT C_MASTERY_FK_SUMMONER FOREIGN KEY (SUMMONER_ID) REFERENCES s_summoner (ID),
  CONSTRAINT C_MASTERY_FK_CHAMPION FOREIGN KEY (CHAMPION_ID) REFERENCES c_champion (ID)
);