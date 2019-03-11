package wsg.lol.controller;

import org.springframework.ui.Model;
import wsg.lol.common.base.ResultDto;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 16:16
 */
public class BaseController {

    protected String resultPage(Model model, ResultDto resultDto) {
        if (resultDto.isSuccess())
            return "base/success";
        model.addAttribute("result", resultDto);
        return "base/error";
    }

    protected String redirect(String redirectUrl) {
        return "redirect:" + redirectUrl;
    }
}
