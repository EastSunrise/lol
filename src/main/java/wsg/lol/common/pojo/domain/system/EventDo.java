package wsg.lol.common.pojo.domain.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDo;
import wsg.lol.common.enums.system.EventStatusEnum;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Base DO for events.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class EventDo extends BaseDo {

    @Id
    private String id;

    @Column
    private EventStatusEnum status;
}
