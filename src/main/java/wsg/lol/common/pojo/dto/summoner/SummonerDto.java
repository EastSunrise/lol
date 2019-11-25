package wsg.lol.common.pojo.dto.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Bean for summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "s_summoner")
public class SummonerDto extends BaseDto {

    /**
     * Encrypted summoner ID. Max length 63 characters.
     */
    @Id
    private String id;

    /**
     * Encrypted account ID. Max length 56 characters.
     */
    @Column
    private String accountId;

    /**
     * Encrypted PUUID. Exact length of 78 characters.
     */
    @Column
    private String puuid;

    /**
     * Summoner name.
     */
    @Column
    private String name;

    /**
     * ID of the summoner icon associated with the summoner.
     */
    @Column
    private Integer profileIconId;

    /**
     * Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp:
     * profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
     */
    @Column
    private Date revisionDate;

    /**
     * Summoner level associated with the summoner.
     */
    @Column
    private Integer summonerLevel;

    @Column
    private Integer score;

    @Column
    private Date lastUpdate;
}
