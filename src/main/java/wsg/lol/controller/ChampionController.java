package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wsg.lol.pojo.dto.query.state.GetChampionDto;
import wsg.lol.pojo.dto.query.state.GetChampionListDto;
import wsg.lol.pojo.dto.state.champion.ChampionDto;
import wsg.lol.service.user.intf.ChampionService;

/**
 * wsg
 *
 * @author wangsigen
 */
@Controller
@RequestMapping("/lol/champion")
public class ChampionController extends BaseController {

    private ChampionService championService;

    @RequestMapping("/index")
    public String championList(GetChampionListDto getChampionListDto, Model model) {
        model.addAttribute("championList", championService.getChampionList(getChampionListDto));
        return templatePath("champions");
    }

    @RequestMapping("/individual")
    public String getChampionInfo(String name, Model model) {
        GetChampionDto getChampionDto = new GetChampionDto();
        getChampionDto.setName(name);
        ChampionDto championDto = championService.getChampionInfo(getChampionDto);
        model.addAttribute("championInfo", championDto);
        return templatePath("individual");
    }

    @Override
    String templatePath(String fileName) {
        return "champion/" + fileName;
    }

    @Autowired
    public void setChampionService(ChampionService championService) {
        this.championService = championService;
    }
}
