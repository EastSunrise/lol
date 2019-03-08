package wsg.lol.data.api;

import wsg.lol.common.constants.annotation.AccessInterval;
import wsg.lol.dto.state.champion.ChampionRotationDto;

/**
 * @author King
 * @date 2019/2/11
 */
public class ChampionV3 extends BaseApi {

    /**
     * wsg 未存DB
     * Returns champion rotations, including free-to-play and low-level free-to-play rotations (REST)
     */
    @AccessInterval
    public static ChampionRotationDto getChampionRotation() {
        return getDataObject("/lol/platform/v3/champion-rotations", ChampionRotationDto.class);
    }
}
