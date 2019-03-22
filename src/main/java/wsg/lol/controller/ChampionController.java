package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wsg.lol.pojo.dto.query.GetChampionDto;
import wsg.lol.pojo.dto.query.GetChampionListDto;
import wsg.lol.pojo.dto.state.champion.ChampionExtDto;
import wsg.lol.service.action.intf.VersionAction;
import wsg.lol.service.service.intf.ChampionService;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 10:33
 */
@Controller
@RequestMapping("/lol/champion")
public class ChampionController extends BaseController {

    @Autowired
    private VersionAction versionAction;

    @Autowired
    private ChampionService championService;

    @RequestMapping("/index")
    public String championList(GetChampionListDto getChampionListDto, Model model) {
        model.addAttribute("championList", championService.getChampionList(getChampionListDto).getChampionDmoList());
        return templatePath("champions");
    }

    @RequestMapping("/build")
    public String buildChampionLib(Model model) {
        return resultPage(model, versionAction.updateChampionLib());
    }

    @RequestMapping("/individual")
    public String getChampionInfo(String name, Model model) {
        GetChampionDto getChampionDto = new GetChampionDto();
        getChampionDto.setName(name);
        ChampionExtDto championExtDto = championService.getChampionInfo(getChampionDto);
        model.addAttribute("championInfo", championExtDto);
        return templatePath("individual");
    }

    @Override
    String templatePath(String fileName) {
        return "champion/" + fileName;
    }
}
