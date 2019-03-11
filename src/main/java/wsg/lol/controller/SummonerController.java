package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wsg.lol.dto.query.GetSummonerDto;
import wsg.lol.dto.result.SummonerResult;
import wsg.lol.service.intf.SummonerService;
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

    @Autowired
    private SummonerService summonerService;

    @RequestMapping("/index")
    public String homePage() {
        return templatePath("index");
    }

    @RequestMapping("/build")
    public String buildSummonerLib(Model model) {
        return resultPage(model, realAction.buildBaseSummonerLibByLeague());
    }

    @RequestMapping("extend")
    public String extendSummonerLib(Model model) {
        return resultPage(model, realAction.extendSummonerLibByMatch());
    }

    @RequestMapping("/search")
    public String searchSummoner(String summonerName) {
        GetSummonerDto getSummonerDto = new GetSummonerDto();
        getSummonerDto.setSummonerName(summonerName);
        SummonerResult summonerResult = summonerService.querySummonerOverview(getSummonerDto);
        return templatePath("summoner");
    }

    @Override
    String templatePath(String fileName) {
        return "summoner/" + fileName;
    }
}
