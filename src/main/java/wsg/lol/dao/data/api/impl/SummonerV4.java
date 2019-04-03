package wsg.lol.dao.data.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wsg.lol.dao.data.api.base.intf.BaseApi;
import wsg.lol.pojo.dto.api.summoner.SummonerDto;
import wsg.lol.pojo.dto.query.GetSummonerDto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author King
 */
@Component
public class SummonerV4 {

    private BaseApi baseApi;

    /**
     * Get a summoner by summoner ID, name, puuid or accountId.
     * <p>
     * ATTENTION Consistently looking up summoner ids that don't exist will result in a blacklist.
     */
    public SummonerDto getSummoner(GetSummonerDto getSummonerDto) {
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
                return baseApi.getObject("/lol/summoner/v4/summoners/" + keyEnum.url, params, SummonerDto.class);
            }
        }
        return null;
    }

    @Autowired
    public void setBaseApi(BaseApi baseApi) {
        this.baseApi = baseApi;
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
