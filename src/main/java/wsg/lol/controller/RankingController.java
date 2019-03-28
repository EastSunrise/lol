package wsg.lol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * wsg
 *
 * @author wangsigen
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
