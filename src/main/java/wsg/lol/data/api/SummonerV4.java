package wsg.lol.data.api;

import wsg.lol.pojo.dmo.summoner.SummonerDmo;
import wsg.lol.pojo.dto.query.GetSummonerDto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/11
 */
public class SummonerV4 extends BaseApi {

    /**
     * Get a summoner by summoner ID, name, puuid or accountId.
     * <p>
     * ATTENTION Consistently looking up summoner ids that don't exist will result in a blacklist.
     */
    public SummonerDmo getSummoner(GetSummonerDto getSummonerDto) {
        Class clazz = getSummonerDto.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(getSummonerDto);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null) {
                KeyEnum keyEnum = Enum.valueOf(KeyEnum.class, field.getName().toUpperCase());
                Map<String, Object> params = new HashMap<>();
                params.put(keyEnum.key, value);
                return getDataObject("/lol/summoner/v4/summoners/" + keyEnum.url, params, SummonerDmo.class);
            }
        }
        return null;
    }

    private enum KeyEnum {
        ID("encryptedSummonerId", "{encryptedSummonerId}"),
        ACCOUNTID("encryptedAccountId", "by-account/{encryptedAccountId}"),
        NAME("summonerName", "by-name/{summonerName}"),
        PUUID("encryptedPUUID", "by-puuid/{encryptedPUUID}");
        String key;
        String url;

        KeyEnum(String key, String url) {
            this.key = key;
            this.url = url;
        }
    }
}
