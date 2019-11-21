package wsg.lol.service.intf;

import wsg.lol.common.base.Result;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.pojo.dto.champion.SpellDto;

import java.util.List;

/**
 * Service for champions.
 *
 * @author Kingen
 */
public interface ChampionService {

    /**
     * Update the data of champions once the version changes.
     */
    Result updateChampions(String version);

    /**
     * Update the spells of champions or summoners.
     */
    Result updateSpells(List<SpellDto> spells, SpellNumEnum... nums);
}
