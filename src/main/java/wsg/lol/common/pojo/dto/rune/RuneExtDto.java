package wsg.lol.common.pojo.dto.rune;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
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

    public List<RuneDto> getRunes() {
        List<RuneDto> runeDtoList = new ArrayList<>();
        for (int i = 0; i < slots.size(); i++) {
            Map<String, List<RuneDto>> slot = slots.get(i);
            List<RuneDto> runes = slot.get(RuneExtDto.RUNES);
            for (int j = 0; j < runes.size(); j++) {
                RuneDto runeDto = runes.get(j);
                runeDto.setTreeId(getId());
                runeDto.setNumX(i);
                runeDto.setNumY(j);
            }
            runeDtoList.addAll(runes);
        }
        return runeDtoList;
    }
}
