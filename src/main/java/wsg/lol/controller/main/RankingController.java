package wsg.lol.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * wsg
 *
 * @author EastSunrise
 */
@Controller
@RequestMapping("/lol/ranking")
public class RankingController extends BaseController {

    @RequestMapping("/index")
    public String apexSummoners() {
        return "ranking/apexSummoners";
    }

    @Override
    String templatePath(String fileName) {
        return "ranking/" + fileName;
    }
}
