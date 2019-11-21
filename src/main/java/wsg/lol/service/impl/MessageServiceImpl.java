package wsg.lol.service.impl;

import org.springframework.stereotype.Service;
import wsg.lol.common.base.Result;
import wsg.lol.common.util.ResultUtils;
import wsg.lol.service.intf.MessageService;

/**
 * @author Kingen
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public void sendMessage(String message) {
        // TODO: (Kingen, 2019/11/21)  send a message.
        ResultUtils.success();
    }

    @Override
    public void sendWarnMessage(Result result) {
        if (!result.isSuccess()) {
            sendMessage(result.getMessage());
        }
    }
}
