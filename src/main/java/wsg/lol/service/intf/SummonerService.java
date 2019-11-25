package wsg.lol.service.intf;

import org.apache.ibatis.session.RowBounds;
import wsg.lol.common.base.Result;

/**
 * Service for summoners.
 *
 * @author Kingen
 */
public interface SummonerService {

    /**
     * Update the older summoners.
     */
    Result updateSummoners(RowBounds rowBounds);
}
