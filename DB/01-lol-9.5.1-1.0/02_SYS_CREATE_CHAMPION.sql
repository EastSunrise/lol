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
  CONSTRAINT C_MASTERY_FK_SUMMONER FOREIGN KEY (SUMMONER_ID) REFERENCES s_summoner (ID)
);