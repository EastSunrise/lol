package wsg.lol.dao.api.impl;

import org.springframework.stereotype.Component;
import wsg.lol.common.pojo.dto.champion.ChampionRotation;
import wsg.lol.dao.api.client.BaseApi;

/**
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/apis#champion-v3">CHAMPION-V3</a>
 */
@Component
public class ChampionV3 extends BaseApi {

    /**
     * Returns champion rotations, including free-to-play and low-level free-to-play rotations (REST)
     *
     * @see <a href="https://developer.riotgames.com/apis#champion-v3/GET_getChampionInfo"/>
     */
    public ChampionRotation getChampionRotation() {
        return this.getObject("/lol/platform/v3/champion-rotations", ChampionRotation.class);
    }
}
