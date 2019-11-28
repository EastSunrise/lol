package wsg.lol.common.pojo.domain.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.system.EventStatusEnum;
import wsg.lol.common.enums.system.EventTypeEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DO for events.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(schema = "lol", name = "t_event")
public class EventDo extends BaseDo {

    @Id
    private Integer id;

    @Column
    private EventTypeEnum type;

    @Column
    private String context;

    @Column
    private EventStatusEnum status;
}