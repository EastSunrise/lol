package wsg.lol.common.pojo.dto.state.rune;

import org.springframework.data.annotation.Id;
import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.base.IJson;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class RuneTreeDto extends BaseDto implements IJson {

    @Id
    private int id;

    private String key;
    private String icon;
    private String name;
    private List<SlotDto> slots;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SlotDto> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDto> slots) {
        this.slots = slots;
    }

    /**
     * wsg
     *
     * @author EastSunrise
     */
    public static class SlotDto {
        private List<RuneDto> runes;

        public List<RuneDto> getRunes() {
            return runes;
        }

        public void setRunes(List<RuneDto> runes) {
            this.runes = runes;
        }

        /**
         * wsg
         *
         * @author EastSunrise
         */
        public static class RuneDto {

            private int id;
            private String key;
            private String icon;
            private String name;
            private String shortDesc;
            private String longDesc;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public String getLongDesc() {
                return longDesc;
            }

            public void setLongDesc(String longDesc) {
                this.longDesc = longDesc;
            }
        }
    }
}
