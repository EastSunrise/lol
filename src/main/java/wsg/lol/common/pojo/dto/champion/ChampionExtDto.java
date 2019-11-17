package wsg.lol.common.pojo.dto.champion;

import wsg.lol.common.pojo.dto.general.ImageDto;
import wsg.lol.common.pojo.dto.state.RecommendedDto;

import java.util.List;

public class ChampionExtDto extends ChampionDto {

    private ImageDto image;
    private List<SkinDto> skins;
    private ChampionStatsDto stats;
    private ChampionSpellDto passive;
    private List<String> allytips;
    private List<String> enemytips;
    private List<ChampionSpellDto> spells;
    private List<RecommendedDto> recommended;

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public List<SkinDto> getSkins() {
        return skins;
    }

    public void setSkins(List<SkinDto> skins) {
        this.skins = skins;
    }

    public ChampionStatsDto getStats() {
        return stats;
    }

    public void setStats(ChampionStatsDto stats) {
        this.stats = stats;
    }

    public ChampionSpellDto getPassive() {
        return passive;
    }

    public void setPassive(ChampionSpellDto passive) {
        this.passive = passive;
    }

    public List<String> getAllytips() {
        return allytips;
    }

    public void setAllytips(List<String> allytips) {
        this.allytips = allytips;
    }

    public List<String> getEnemytips() {
        return enemytips;
    }

    public void setEnemytips(List<String> enemytips) {
        this.enemytips = enemytips;
    }

    public List<ChampionSpellDto> getSpells() {
        return spells;
    }

    public void setSpells(List<ChampionSpellDto> spells) {
        this.spells = spells;
    }

    public List<RecommendedDto> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<RecommendedDto> recommended) {
        this.recommended = recommended;
    }
}
