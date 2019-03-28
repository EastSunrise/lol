package wsg.lol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author King
 */
@Controller
@RequestMapping("/lol/setting")
public class SettingController extends BaseController {
    @Override
    String templatePath(String fileName) {
        return "setting/" + fileName;
    }
}
