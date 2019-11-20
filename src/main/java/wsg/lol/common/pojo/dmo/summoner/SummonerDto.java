package wsg.lol.common.pojo.dmo.summoner;

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
    private Date revisionDate;
    private Integer summonerLevel;
    private Date lastCheckedTime;
}
