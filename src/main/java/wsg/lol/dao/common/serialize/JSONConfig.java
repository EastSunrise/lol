package wsg.lol.dao.common.serialize;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.Configuration;
import wsg.lol.common.enums.champion.ChampionTagEnum;
import wsg.lol.common.enums.champion.ChampionTipEnum;
import wsg.lol.common.enums.champion.SpellNumEnum;
import wsg.lol.common.enums.match.*;
import wsg.lol.common.enums.share.*;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.enums.system.RegionalRoutingEnum;

/**
 * Global config for JSON.
 *
 * @author Kingen
 */
@Configuration
public class JSONConfig {

    static {
        {
            Class<?>[] classes = new Class<?>[]{
                    ChampionTagEnum.class,
                    ChampionTipEnum.class,
                    SpellNumEnum.class,
                    BuildingTypeEnum.class,
                    GameModeEnum.class,
                    GameTypeEnum.class,
                    LaneTypeEnum.class,
                    LevelUpTypeEnum.class,
                    MatchEventTypeEnum.class,
                    MatchLaneEnum.class,
                    MatchQueueEnum.class,
                    MatchRoleEnum.class,
                    MonsterSubTypeEnum.class,
                    MonsterTypeEnum.class,
                    TeamIdEnum.class,
                    TeamResultEnum.class,
                    TowerTypeEnum.class,
                    WardTypeEnum.class,
                    BlockTypeEnum.class,
                    ImageGroupEnum.class,
                    ItemTagEnum.class,
                    MapEnum.class,
                    MessageSeverityEnum.class,
                    RankQueueEnum.class,
                    RecommendedTypeEnum.class,
                    SeasonEnum.class,
                    ServiceEnum.class,
                    ServiceStatusEnum.class,
                    DivisionEnum.class,
                    TierEnum.class,
                    EventStatusEnum.class,
                    EventTypeEnum.class,
                    RegionEnum.class,
                    RegionalRoutingEnum.class
            };
            ParserConfig instance = ParserConfig.getGlobalInstance();
            for (Class<?> clazz : classes) {
                instance.putDeserializer(clazz, CustomEnumDeserializer.instance);
            }
        }
    }
}
