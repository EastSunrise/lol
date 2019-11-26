package wsg.lol.common.pojo.parser;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Deserializer for bans of the team.
 *
 * @author Kingen
 */
public class BansDeserializer implements ObjectDeserializer {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        List<TeamBansDto> teamBans = parser.parseArray(TeamBansDto.class);
        Map<Integer, Integer> map = new HashMap<>();
        for (TeamBansDto teamBan : teamBans) {
            map.put(teamBan.getPickTurn(), teamBan.getChampionId());
        }
        return (T) map;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    @Data
    private static class TeamBansDto {
        /**
         * Turn during which the champion was banned.
         */
        private Integer pickTurn;

        /**
         * Banned championId.
         */
        private Integer championId;
    }
}
