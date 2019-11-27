package wsg.lol.common.pojo.dto.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.Date;

/**
 * Bean for summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SummonerDto extends BaseDto {

    private String id;

    private String accountId;

    private String puuid;

    private String name;

    private Integer profileIconId;

    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp:
     * profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     */
    private Date revisionDate;

    private Integer summonerLevel;

    private Integer score;

    private Date lastUpdate;

    private Date lastMatch;
}
