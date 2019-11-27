package wsg.lol.common.pojo.dto.share;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * DTO for recommended data.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendedExtDto extends RecommendedDto {

    private List<BlockDto> blocks;
}
