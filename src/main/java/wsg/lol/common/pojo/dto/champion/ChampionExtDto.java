package wsg.lol.common.pojo.dto.champion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.pojo.dto.item.ImageDto;
import wsg.lol.common.pojo.dto.item.RecommendedExtDto;

import java.util.List;

/**
 * DTO for all of info of champion.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChampionExtDto extends ChampionDto {

    private ImageDto image;
    private List<SkinDto> skins;
    private ChampionStatsDto stats;
    private SpellDto passive;
    private List<String> allytips;
    private List<String> enemytips;
    private List<SpellDto> spells;
    private List<RecommendedExtDto> recommended;
}
