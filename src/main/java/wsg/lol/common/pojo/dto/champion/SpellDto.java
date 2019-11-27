package wsg.lol.common.pojo.dto.champion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.pojo.dto.share.ImageDto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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

    private Integer championId;

    @JSONField(name = "id")
    private String key;

    private String name;

    private SpellNumEnum num;

    private String description;

    private Integer maxrank;

    private String tooltip;

    private Map<String, String[]> leveltip;

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

    // just for summoner spells.

    private Integer summonerLevel;

    private GameModeEnum[] modes;

    private ImageDto image;

    /**
     * set to <expression>{@link #championId} * 100 + {@link #num}</expression> if this is a spell of champion.
     */
    public static int generateId(int championId, @NotNull SpellNumEnum spellNum) {
        if (!spellNum.isChampionSpell()) {
            throw new IllegalArgumentException("Can't calculate the key if it isn't a champion spell.");
        }
        return championId * 100 + spellNum.ordinal();

    }

    @Data
    private static class SpellVarDto {
        private String link;
        private List<Double> coeff;
        private String key;
    }
}