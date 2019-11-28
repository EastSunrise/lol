package wsg.lol.common.pojo.domain.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.annotation.Flatten;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.enums.match.GameModeEnum;
import wsg.lol.common.pojo.dto.champion.SpellDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DO for the spells of champions or summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "c_spell")
public class SpellDo extends BaseDo {

    @Id
    private Integer id;

    private Integer championId;

    @Column(name = "`key`")
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
    @Flatten
    private List<String> leveltipLabel;

    @Column
    @Flatten
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
    private List<SpellDto.SpellVarDto> vars;

    @Column
    private String costType;

    @Column
    private Integer maxammo;

    @Column
    private String resource;

    @Column
    private Integer summonerLevel;

    @Column
    private GameModeEnum[] modes;

    /**
     * set to <expression>{@link #championId} * 100 + {@link #num}</expression> if this is a spell of champion.
     */
    public static int generateId(int championId, @NotNull SpellNumEnum spellNum) {
        if (!spellNum.isChampionSpell()) {
            throw new IllegalArgumentException("Can't calculate the key if it isn't a champion spell.");
        }
        return championId * 100 + spellNum.ordinal();
    }
}