package wsg.lol.service.common;

import java.util.List;

/**
 * Task for service.
 *
 * @author Kingen
 */
public interface ServiceTask<T> {

    List<? extends T> run();
}
