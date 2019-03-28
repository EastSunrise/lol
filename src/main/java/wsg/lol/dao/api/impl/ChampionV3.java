package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.annotation.AccessApi;
import wsg.lol.dao.api.base.BaseApi;
import wsg.lol.pojo.dto.api.champion.ChampionRotationDto;

/**
 * @author King
 */
@Component
public class ChampionV3 extends BaseApi {

    /**
     * wsg 未存DB
     * Returns champion rotations, including free-to-play and low-level free-to-play rotations (REST)
     */
    @AccessApi
    public ChampionRotationDto getChampionRotation() {
        return getObject("/lol/platform/v3/champion-rotations", ChampionRotationDto.class);
    }
}
