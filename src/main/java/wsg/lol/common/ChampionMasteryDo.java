package wsg.lol.common;

import wsg.lol.common.base.BaseDo;

import java.util.Date;

public class ChampionMasteryDo extends BaseDo {

    private String summonerId;

    private Integer championId;

    private Byte chestGranted;

    private Integer championLevel;

    private Integer championPoints;

    private Integer championPointsUntilNextLevel;

    private Integer championPointsSinceLastLevel;

    private Integer tokensEarned;

    private Date lastPlayTime;
}