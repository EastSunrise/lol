package wsg.lol.common.pojo.transfer;

import wsg.lol.common.base.BaseDo;
import wsg.lol.common.base.BaseDto;

/**
 * Interface to transfer the object.
 *
 * @author Kingen
 */
public interface Transfer<F extends BaseDto, T extends BaseDo> {

    /**
     * Transfer an data transfer object to a domain object.
     */
    T transfer(F f);
}
