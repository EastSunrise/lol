package wsg.lol.dto.state.champion;

import wsg.lol.common.base.QueryDto;
import wsg.lol.common.enums.impl.others.ChampionTypeEnum;
import wsg.lol.common.enums.impl.others.PositionEnum;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-27 15:28
 */
public class GetChampionListDto extends QueryDto {

    private ChampionTypeEnum heroType;

    private PositionEnum position;

    private Boolean isRotating;

    public ChampionTypeEnum getHeroType() {
        return heroType;
    }

    public void setHeroType(ChampionTypeEnum heroType) {
        this.heroType = heroType;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public Boolean getIsRotating() {
        return isRotating;
    }

    public void setIsRotating(Boolean isRotating) {
        this.isRotating = isRotating;
    }
}
