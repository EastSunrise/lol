package wsg.lol.service.intf;

import wsg.lol.common.base.Result;

/**
 * Service for messages.
 *
 * @author Kingen
 */
public interface MessageService {

    /**
     * Send a message.
     */
    void sendMessage(String message);

    /**
     * Send messages of warning.
     */
    void sendWarnMessage(Result result);
}
