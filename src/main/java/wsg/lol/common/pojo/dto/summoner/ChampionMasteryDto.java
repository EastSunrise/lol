package wsg.lol.common.pojo.dto.summoner;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.constant.JSONConst;

import java.util.Date;

/**
 * Bean for summoners' mastery of champions.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionMasteryDto extends BaseDto {

    private String summonerId;
    private Integer championId;
    private Boolean chestGranted;
    private Integer championLevel;
    private Integer championPoints;
    private Integer championPointsUntilNextLevel;
    private Integer championPointsSinceLastLevel;
    private Integer tokensEarned;

    @JSONField(format = JSONConst.DATE_FORMAT)
    private Date lastPlayTime;
}
