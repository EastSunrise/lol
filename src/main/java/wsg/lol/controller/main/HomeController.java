package wsg.lol.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Controller
@RequestMapping("/lol")
public class HomeController extends BaseController {

    @RequestMapping("/index")
    public String homePage() {
        return redirect("/lol/summoner/index");
    }

    @Override
    String templatePath(String fileName) {
        return fileName;
    }
}
