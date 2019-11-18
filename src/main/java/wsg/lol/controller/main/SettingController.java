package wsg.lol.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * // TODO: (Kingen, 2019/11/18)
 *
 * @author EastSunrise
 */
@Controller
@RequestMapping("/lol/setting")
public class SettingController extends BaseController {
    @Override
    String templatePath(String fileName) {
        return "setting/" + fileName;
    }
}
