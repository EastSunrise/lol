package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wsg.lol.dmo.summoner.SummonerDmo;
import wsg.lol.service.intf.SummonerService;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:21
 */
@Controller
@RequestMapping("/lol")
public class SummonerController {

    @Autowired
    private SummonerService summonerService;

    @RequestMapping("/index")
    public String hello() {
        return "helloWorld";
    }

    @RequestMapping("/summoner/index")
    public void getSummoner(Model model, String id) {
        SummonerDmo summonerDmo = summonerService.getSummonerById(id);
        model.addAttribute("account", summonerDmo.getAccountId());
    }
}
