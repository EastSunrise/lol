package wsg.lol.common.pojo.domain.summoner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * DO for summoners' masteries of champions.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "s_mastery")
public class ChampionMasteryDo extends BaseDo {

    @Id
    private String summonerId;

    @Id
    private Integer championId;

    @Column
    private Boolean chestGranted;

    @Column
    private Integer championLevel;

    @Column
    private Integer championPoints;

    @Column
    private Integer championPointsUntilNextLevel;

    @Column
    private Integer championPointsSinceLastLevel;

    @Column
    private Integer tokensEarned;

    @Column
    private Date lastPlayTime;
}