package wsg.lol.dao.data.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.dao.data.api.base.intf.BaseApi;
import wsg.lol.pojo.annotation.AccessApi;
import wsg.lol.pojo.dto.api.champion.ChampionRotationDto;

/**
 * @author King
 */
@Component
public class ChampionV3 {

    private BaseApi baseApi;

    /**
     * wsg 未存DB
     * Returns champion rotations, including free-to-play and low-level free-to-play rotations (REST)
     */
    @AccessApi
    public ChampionRotationDto getChampionRotation() {
        return baseApi.getObject("/lol/platform/v3/champion-rotations", ChampionRotationDto.class);
    }

    @Autowired
    public void setBaseApi(BaseApi baseApi) {
        this.baseApi = baseApi;
    }
}
