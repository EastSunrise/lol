package wsg.lol.common.pojo.dto.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;

/**
 * DTO for events.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EventDto extends BaseDto {

    private Integer id;

    private EventTypeEnum type;

    private String context;

    private EventStatusEnum status;
}
