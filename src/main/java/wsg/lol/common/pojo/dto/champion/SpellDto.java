package wsg.lol.common.pojo.dto.champion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.pojo.dto.share.ImageDto;

import java.util.List;

/**
 * DTO for the spells of champions or summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpellDto extends BaseDto {

    @JSONField(name = "key")
    private Integer id;

    @JSONField(name = "id")
    private String key;

    private String name;

    private String description;

    private Integer maxrank;

    private String tooltip;

    private LevelTipDto leveltip;

    private List<Double> cooldown;

    private String cooldownBurn;

    private List<Integer> cost;

    private String costBurn;

    private List<Integer> range;

    private String rangeBurn;

    private String datavalues;

    private List<List<Integer>> effect;

    private List<String> effectBurn;

    private List<SpellVarDto> vars;

    private String costType;

    private Integer maxammo;

    private String resource;

    private Integer summonerLevel;

    private GameModeEnum[] modes;

    private ImageDto image;

    @EqualsAndHashCode(callSuper = true)
    @Data
    private static class LevelTipDto extends BaseDto {
        private List<String> label;
        private List<String> effect;
    }

    @Data
    public static class SpellVarDto {
        private String link;
        private List<Double> coeff;
        private String key;
    }
}