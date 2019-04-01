package wsg.lol.pojo.dto.state.spell;

import wsg.lol.pojo.base.BaseDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
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
     * @author wangsigen
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
