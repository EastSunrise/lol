-- 召唤师 Summoner
CREATE TABLE s_summoner
(
  ID                VARCHAR(63) COMMENT '主键，Encrypted',
  ACCOUNT_ID        VARCHAR(56) COMMENT 'Encrypted',
  PUUID             CHAR(78) COMMENT 'Encrypted',
  NAME              VARCHAR(32) COMMENT '召唤师昵称',
  PROFILE_ICON_ID   INT COMMENT '头像ID',
  REVISION_DATE     TIMESTAMP COMMENT '修改时间',
  SUMMONER_LEVEL    INT COMMENT '召唤师等级',
  LAST_CHECKED_TIME TIMESTAMP COMMENT '最近一次用来扩展数据的时间，默认1970.1.1',
  CONSTRAINT S_SUMMONER_PK PRIMARY KEY (ID)
);