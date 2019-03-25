package wsg.lol.dao.state;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.common.utils.FileUtil;
import wsg.lol.dao.config.Config;
import wsg.lol.pojo.dmo.champion.SpellDmo;
import wsg.lol.pojo.dto.state.champion.ChampionExtDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class ChampionFile {

    @Autowired
    private Config config;

    public List<ChampionExtDto> getChampions() {
        JSONObject data = getDataByKey(KeyEnum.championFull).getJSONObject("data");
        List<ChampionExtDto> championExtDtoList = new LinkedList<>();
        for (Object value : data.values()) {
            ChampionExtDto championExtDto = new ChampionExtDto();
            championExtDto.parseFromJSONObject((JSONObject) value);
            championExtDtoList.add(championExtDto);
        }
        return championExtDtoList;
    }

    public List<SpellDmo> getSummonerSpells() {
        JSONObject data = getDataByKey(KeyEnum.summoner).getJSONObject("data");
        for (Map.Entry<String, Object> entry : data.entrySet()) {

        }
        return null;
    }

    private JSONObject getDataByKey(KeyEnum key) {
        return FileUtil.readJSONObject(FileUtil.concat2Path(getDir(), key + ".json"));
    }

    private enum KeyEnum {
        championFull, summoner
    }

    private String getDir() {
        return FileUtil.concat2Path(config.getDataDir(), config.getLatestVersion(), "data", config.getLanguage());
    }
}
