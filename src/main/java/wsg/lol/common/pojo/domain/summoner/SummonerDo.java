package wsg.lol.common.pojo.domain.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * DO for summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "s_summoner")
public class SummonerDo extends BaseDo {

    @Id
    private String id;

    @Column
    private String accountId;

    @Column
    private String puuid;

    @Column
    private String name;

    @Column
    private Integer profileIconId;

    @Column
    private Date revisionDate;

    @Column
    private Integer summonerLevel;

    @Column
    private Integer score;

    @Column
    private Date lastUpdate;

    @Column
    private Date lastMatch;
}