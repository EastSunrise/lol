package wsg.lol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-11 16:12
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
