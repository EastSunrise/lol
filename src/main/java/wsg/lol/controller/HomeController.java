package wsg.lol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * todo
 *
 * @author EastSunrise
 */
@Controller
@RequestMapping("/lol")
public class HomeController extends BaseController {

    @RequestMapping("/index")
    public String homePage() {
        return redirect();
    }

    @Override
    String templatePath(String fileName) {
        return fileName;
    }
}
