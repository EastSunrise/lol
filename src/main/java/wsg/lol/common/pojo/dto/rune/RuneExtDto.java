package wsg.lol.common.pojo.dto.rune;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * Bean for extension of rune trees.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RuneExtDto extends RuneTreeDto {

    public static final String RUNES = "runes";

    private List<Map<String, List<RuneDto>>> slots;
}
