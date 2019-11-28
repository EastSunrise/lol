package wsg.lol.common.pojo.dto.champion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.ChampionTagEnum;

/**
 * DTO for base info of champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionDto extends BaseDto {

    @JSONField(name = "key")
    private Integer id;

    @JSONField(name = "id")
    private String key;

    private String name;
    private String title;
    private String lore;
    private String blurb;
    private String partype;
    private ChampionTagEnum[] tags;

    private ChampionInfoDto info;

    @EqualsAndHashCode(callSuper = true)
    @Data
    private static class ChampionInfoDto extends BaseDto {
        private Integer attack;
        private Integer magic;
        private Integer defense;
        private Integer difficulty;
    }
}
