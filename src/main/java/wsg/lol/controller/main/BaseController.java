package wsg.lol.controller.main;

import org.springframework.ui.Model;
import wsg.lol.common.base.Result;

/**
 * wsg
 *
 * @author EastSunrise
 */
public abstract class BaseController {

    String resultPage(Model model, Result result) {
        if (result.isSuccess())
            return "base/success";
        model.addAttribute("result", result);
        return "base/error";
    }

    abstract String templatePath(String fileName);

    protected String redirect(String redirectUrl) {
        return "redirect:" + redirectUrl;
    }
}
