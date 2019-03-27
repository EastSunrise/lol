package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.pojo.dto.query.GetSummonerDto;
import wsg.lol.pojo.result.SummonerResult;
import wsg.lol.service.user.intf.SummonerService;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-07 15:21
 */
@Controller
@RequestMapping("/lol/summoner")
public class SummonerController extends BaseController {

    private SummonerService summonerService;

    @RequestMapping("/index")
    public String homePage() {
        return templatePath("index");
    }

    @RequestMapping("/search")
    public String searchSummoner(String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            return templatePath("index");
        }
        GetSummonerDto getSummonerDto = new GetSummonerDto();
        getSummonerDto.setName(name);
        BaseResult baseResult = summonerService.querySummonerOverview(getSummonerDto);
        if (!baseResult.isSuccess()) {
            return resultPage(model, baseResult);
        }
        SummonerResult summonerResult = (SummonerResult) baseResult;
        model.addAttribute("summoner", summonerResult.getSummonerDmo());
        model.addAttribute("position", summonerResult.getPositionDmo());
        return templatePath("individual");
    }

    @Override
    String templatePath(String fileName) {
        return "summoner/" + fileName;
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
