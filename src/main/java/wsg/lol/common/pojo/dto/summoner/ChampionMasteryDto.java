package wsg.lol.common.pojo.dto.summoner;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.constant.JSONConst;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Bean for summoners' mastery of champions.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "s_mastery")
public class ChampionMasteryDto extends BaseDto {

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

    @JSONField(format = JSONConst.DATE_FORMAT)
    @Column
    private Date lastPlayTime;
}
