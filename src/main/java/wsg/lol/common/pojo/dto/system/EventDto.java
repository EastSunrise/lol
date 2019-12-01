package wsg.lol.common.pojo.dto.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.system.EventStatusEnum;

/**
 * DTO for events.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EventDto extends BaseDto {

    private String id;

    private EventStatusEnum status;
}
