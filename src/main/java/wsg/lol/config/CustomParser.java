package wsg.lol.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
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
import wsg.lol.common.pojo.serialize.CustomEnumDeserializer;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Global config for JSON.
 *
 * @author Kingen
 */
public class CustomParser {

    public static final ParserConfig customConfig = new ParserConfig();

    public static final ParserConfig defaultConfig = new ParserConfig();

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
            for (Class<?> clazz : classes) {
                customConfig.putDeserializer(clazz, CustomEnumDeserializer.instance);
            }
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz, customConfig);
    }

    public static <T> T parseObject(String text, Class<T> clazz, ExtraProcessor processor) {
        return JSON.parseObject(text, clazz, customConfig, processor, JSON.DEFAULT_PARSER_FEATURE);
    }

    public static <T> T parseObject(String text, Type clazz, ParseProcess processor) {
        return JSON.parseObject(text, clazz, customConfig, processor, JSON.DEFAULT_PARSER_FEATURE);
    }

    public static <T> T parseObjectDefault(String text, Class<T> clazz, Feature... features) {
        return JSON.parseObject(text, clazz, defaultConfig, features);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        ParserConfig.global = customConfig;
        return JSON.parseArray(text, clazz);
    }

    public static <T> List<T> parseArrayDefault(String text, Class<T> clazz) {
        ParserConfig.global = defaultConfig;
        return JSON.parseArray(text, clazz);
    }
}
