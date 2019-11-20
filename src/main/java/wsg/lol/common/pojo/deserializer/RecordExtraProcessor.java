package wsg.lol.common.pojo.deserializer;

import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Record the extra data which not transferred.
 *
 * @author Kingen
 */
public class RecordExtraProcessor implements ExtraProcessor {

    private static Map<String, Set<Object>> extraMap = new HashMap<>();
    private static String[] excludedFields = new String[]{
            "wsg.lol.common.pojo.dto.champion.ChampionExtDto@info",// todo: to solve
            "wsg.lol.common.pojo.dto.champion.SpellDto@leveltip",
            "wsg.lol.common.pojo.dto.item.ItemExtDto@gold",

            "wsg.lol.dao.dragon.impl.DragonDaoImpl$FileDto@keys", // keys of champions.
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$FileDto@basic", // basic for items
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$FileDto@groups", // groups of items.
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$FileDto@tree", // trees of items.

            "wsg.lol.dao.dragon.impl.DragonDaoImpl$FileDto@type", // types of the data: champion, item, profileicon, summoner.
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$FileDto@format", // format of the data of champions: full.
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$FileDto@version", // version of the data.
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$ImageExtDto@id", // ids of the profile icons.
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$ImageExtDto@MapName", // names of maps.
            "wsg.lol.dao.dragon.impl.DragonDaoImpl$ImageExtDto@MapId", // ids of maps.
    };

    private Logger logger;

    public RecordExtraProcessor(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static Map<String, Set<Object>> getExtraMap() {
        return extraMap;
    }

    @Override
    public void processExtra(Object object, String key, Object value) {
        String field = object.getClass().getName() + "@" + key;
        if (ArrayUtils.contains(excludedFields, field)) {
            return;
        }
        Set<Object> values = extraMap.get(field);
        if (values == null) {
            extraMap.put(field, new HashSet<>(Collections.singleton(value)));
            logger.info("Extra field. field: {}; value: {}.", field, value);
        } else {
            if (values.add(value)) {
                logger.info("Extra field: field, {}; value, {}.", field, value);
            }
        }
    }
}
