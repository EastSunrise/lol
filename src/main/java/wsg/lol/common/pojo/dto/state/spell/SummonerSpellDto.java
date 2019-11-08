package wsg.lol.common.pojo.dto.state.spell;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class SummonerSpellDto extends BaseSpellDto {

    private String key;
    private int summonerLevel;
    private List<String> modes;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }
}
