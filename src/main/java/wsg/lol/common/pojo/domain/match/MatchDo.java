package wsg.lol.common.pojo.domain.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.enums.match.GameTypeEnum;
import wsg.lol.common.enums.share.MapEnum;
import wsg.lol.common.enums.share.SeasonEnum;
import wsg.lol.common.enums.system.PlatformRoutingEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * DTO for a match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "m_match")
public class MatchDo extends BaseDo {

    @Id
    private Long gameId;

    @Column
    private PlatformRoutingEnum platformId;

    @Column
    private Date gameCreation;

    @Column
    private Integer gameDuration;

    @Column
    private MapEnum mapId;

    @Column
    private SeasonEnum seasonId;

    @Column
    private String gameVersion;

    @Column
    private GameModeEnum gameMode;

    @Column
    private GameTypeEnum gameType;

    @Column
    private Integer frameInterval;
}