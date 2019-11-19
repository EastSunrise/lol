package wsg.lol.common.pojo.dto.rune;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * // TODO: (Kingen, 2019/11/18)
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RuneExtDto extends RuneTreeDto {

    public static final String RUNES = "runes";

    //    @JSONField(deserialize = false)
    private List<Map<String, List<RuneDto>>> slots;
}
