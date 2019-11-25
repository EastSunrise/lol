package wsg.lol.common.pojo.dto.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean for events.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "t_event")
public class EventDto extends BaseDto {

    @Id
    private Integer id;

    @Column
    private EventTypeEnum type;

    @Column
    private String context;

    @Column
    private EventStatusEnum status;
}
