package wsg.lol.common.pojo.dto.share;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.pojo.domain.share.RuneDo;
import wsg.lol.common.pojo.transfer.ObjectTransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DTO for extension of rune trees.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RuneExtDto extends RuneTreeDto {

    public static final String RUNES = "runes";

    private List<Map<String, List<RuneDto>>> slots;

    public List<RuneDo> getRunes() {
        List<RuneDo> runeDoList = new ArrayList<>();
        for (int i = 0; i < slots.size(); i++) {
            Map<String, List<RuneDto>> slot = slots.get(i);
            List<RuneDto> runes = slot.get(RuneExtDto.RUNES);
            for (int j = 0; j < runes.size(); j++) {
                RuneDo runeDo = ObjectTransfer.transferDto(runes.get(j), RuneDo.class);
                runeDo.setTreeId(getId());
                runeDo.setNumX(i);
                runeDo.setNumY(j);
                runeDoList.add(runeDo);
            }
        }
        return runeDoList;
    }
}
