package wsg.lol.common.pojo.dto.champion;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.pojo.dto.share.ImageDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Bean for spells of champions or summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_spell")
public class SpellDto extends BaseDto {

    @Id
    @JSONField(name = "key")
    private Integer id;
    @Column
    private Integer championId;
    @Column(name = "`key`")
    @JSONField(name = "id")
    private String key;
    @Column
    private String name;
    @Column
    private SpellNumEnum num;
    @Column
    private String description;
    @Column
    private Integer maxrank;
    @Column
    private String tooltip;
    @Column
    private List<String> leveltipLabel;
    @Column
    private List<String> leveltipEffect;
    @Column
    private List<Double> cooldown;
    @Column
    private String cooldownBurn;
    @Column
    private List<Integer> cost;
    @Column
    private String costBurn;
    @Column(name = "`range`")
    private List<Integer> range;
    @Column
    private String rangeBurn;
    @Column
    private String datavalues;
    @Column
    private List<List<Integer>> effect;
    @Column
    private List<String> effectBurn;
    @Column
    private List<SpellVarDto> vars;
    @Column
    private String costType;
    @Column
    private Integer maxammo;
    @Column
    private String resource;

    // just for summoner spells.
    @Column
    private Integer summonerLevel;
    @Column
    private GameModeEnum[] modes;

    @Transient
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