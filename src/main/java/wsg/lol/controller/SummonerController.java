package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wsg.lol.service.scheduler.intf.RealAction;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:21
 */
@Controller
@RequestMapping("/lol/summoner")
public class SummonerController extends BaseController {

    @Autowired
    private RealAction realAction;

    @RequestMapping("/index")
    public String hello() {
        return "summoner/index";
    }

    @RequestMapping("/search")
    public String searchSummoner(String summonerName, Model model) {
        model.addAttribute("name", summonerName);
        return "summoner/individual";
    }

    @RequestMapping("/build")
    public String buildSummonerLib(Model model) {
        return resultPage(model, realAction.buildApexSummonerLib());
    }
}
