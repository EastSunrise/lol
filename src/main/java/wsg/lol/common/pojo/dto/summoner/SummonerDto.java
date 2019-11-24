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

    /**
     * Encrypted summoner ID. Max length 63 characters.
     */
    private String id;

    /**
     * Encrypted account ID. Max length 56 characters.
     */
    private String accountId;

    /**
     * Encrypted PUUID. Exact length of 78 characters.
     */
    private String puuid;

    /**
     * Summoner name.
     */
    private String name;

    /**
     * ID of the summoner icon associated with the summoner.
     */
    private Integer profileIconId;

    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp:
     * profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     */
    private Date revisionDate;

    /**
     * Summoner level associated with the summoner.
     */
    private Integer summonerLevel;

    private Integer score;

    private Date lastUpdate;
}
