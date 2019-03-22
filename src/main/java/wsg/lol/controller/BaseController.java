package wsg.lol.controller;

import org.springframework.ui.Model;
import wsg.lol.pojo.base.BaseResult;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 16:16
 */
public abstract class BaseController {

    String resultPage(Model model, BaseResult baseResult) {
        if (baseResult.isSuccess())
            return "base/success";
        model.addAttribute("result", baseResult);
        return "base/error";
    }

    abstract String templatePath(String fileName);

    protected String redirect(String redirectUrl) {
        return "redirect:" + redirectUrl;
    }
}
