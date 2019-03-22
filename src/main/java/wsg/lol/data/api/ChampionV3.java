package wsg.lol.data.api;

import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.pojo.dto.state.champion.ChampionRotationDto;

/**
 * @author King
 * @date 2019/2/11
 */
@Component
public class ChampionV3 extends BaseApi {

    /**
     * wsg 未存DB
     * Returns champion rotations, including free-to-play and low-level free-to-play rotations (REST)
     */
    @AccessApi
    public ChampionRotationDto getChampionRotation() {
        return getDataObject("/lol/platform/v3/champion-rotations", ChampionRotationDto.class);
    }
}
