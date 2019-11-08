package wsg.lol.common.pojo.dto.state.spell;

import wsg.lol.common.pojo.base.BaseDto;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class ChampionSpellDto extends BaseSpellDto {

    private LevelTipDto leveltip;

    public LevelTipDto getLeveltip() {
        return leveltip;
    }

    public void setLeveltip(LevelTipDto leveltip) {
        this.leveltip = leveltip;
    }

    /**
     * wsg
     *
     * @author EastSunrise
     */
    public static class LevelTipDto extends BaseDto {

        private List<String> label;
        private List<String> effect;

        public List<String> getLabel() {
            return label;
        }

        public void setLabel(List<String> label) {
            this.label = label;
        }

        public List<String> getEffect() {
            return effect;
        }

        public void setEffect(List<String> effect) {
            this.effect = effect;
        }
    }
}
