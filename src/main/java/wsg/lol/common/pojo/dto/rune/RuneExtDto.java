package wsg.lol.common.pojo.dto.rune;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
public class RuneExtDto extends RuneTreeDto {

    @JSONField(deserialize = false)
    private RuneDto[][] slots;

    public RuneDto[][] getSlots() {
        return slots;
    }

    public void setSlots(RuneDto[][] slots) {
        this.slots = slots;
    }
}
