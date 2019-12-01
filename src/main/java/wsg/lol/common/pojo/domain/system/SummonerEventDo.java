package wsg.lol.common.pojo.domain.system;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * DO for events of summoners.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_event_summoner")
public class SummonerEventDo extends EventDo {
}
