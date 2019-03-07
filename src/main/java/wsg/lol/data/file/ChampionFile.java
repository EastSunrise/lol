package wsg.lol.data.file;

import com.alibaba.fastjson.JSONObject;
import wsg.lol.common.constants.DefaultConfig;
import wsg.lol.common.utils.FileUtil;
import wsg.lol.dto.state.champion.ChampionExtDto;

import java.util.LinkedList;
import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-28 11:44
 */
public class ChampionFile extends BaseFile {

    private static final String DIR = FileUtil.concat2Path(CDN_LOCAL, LATEST_VERSION, "data",
            DefaultConfig.getLanguage());

    public static List<ChampionExtDto> getChampions() {
        JSONObject data = getDataByKey(KeyEnum.championFull).getJSONObject("data");
        List<ChampionExtDto> championExtDtoList = new LinkedList<>();
        for (Object value : data.values()) {
            ChampionExtDto championExtDto = new ChampionExtDto();
            championExtDto.parseFromJSONObject((JSONObject) value);
            championExtDtoList.add(championExtDto);
        }
        return championExtDtoList;
    }

    private static JSONObject getDataByKey(KeyEnum key) {
        return FileUtil.readJSONObject(FileUtil.concat2Path(DIR, key + ".json"));
    }

    private enum KeyEnum {
        championFull;
    }
}
