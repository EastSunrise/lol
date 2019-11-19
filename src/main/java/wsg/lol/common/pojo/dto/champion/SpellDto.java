package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.pojo.dto.general.ImageDto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
@Data
public class SpellDto extends BaseDto {

    private String id;
    private Integer championId;
    private Integer key;
    private String name;
    private SpellNumEnum num;
    private String description;
    private Integer maxrank;
    private String tooltip;
    // TODO: (Kingen, 2019/11/18)
    private List<String> leveltipLabel;
    private List<String> leveltipEffect;
    private Double[] cooldown;
    private String cooldownBurn;
    private Integer[] cost;
    private String costBurn;
    private Integer[] range;
    private String rangeBurn;
    private String datavalues;// TODO: (Kingen, 2019/11/18) all are '{}'
    private Integer[][] effect;
    private String[] effectBurn;
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
    public static int calcKey(int championId, @NotNull SpellNumEnum spellNum) {
        if (!spellNum.isChampionSpell()) {
            throw new IllegalArgumentException("Can't calculate the key if it isn't a champion spell.");
        }
        return championId * 100 + spellNum.ordinal();

    }

    static class SpellVarDto {
        private String link;
        private List<Double> coeff;
        private String key;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public List<Double> getCoeff() {
            return coeff;
        }

        public void setCoeff(List<Double> coeff) {
            this.coeff = coeff;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}