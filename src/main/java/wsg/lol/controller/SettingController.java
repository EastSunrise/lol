package wsg.lol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author King
 * @date 2019/3/9
 */
@Controller
@RequestMapping("/lol/setting")
public class SettingController extends BaseController {
    @Override
    String templatePath(String fileName) {
        return "setting/" + fileName;
    }
}
