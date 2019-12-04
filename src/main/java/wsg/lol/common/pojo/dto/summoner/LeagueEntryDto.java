package wsg.lol.common.pojo.dto.summoner;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;

/**
 * DTO for entries of leagues.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeagueEntryDto extends BaseDto {

    private String summonerId;

    private RankQueueEnum queueType;

    private String leagueId;

    private TierEnum tier;

    private DivisionEnum rank;

    private String summonerName;

    private Boolean hotStreak;

    private Integer wins;

    private Boolean veteran;

    private Integer losses;

    private Boolean inactive;

    private Boolean freshBlood;

    private Integer leaguePoints;

    @JSONField(name = "miniSeries")
    private MiniSeriesDto series;

    @EqualsAndHashCode(callSuper = true)
    @Data
    private static class MiniSeriesDto extends BaseDto {
        private Integer target;
        private Integer wins;
        private Integer losses;
        private String progress;
    }
}
