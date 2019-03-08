package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wsg.lol.service.scheduler.intf.VersionAction;

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

    @RequestMapping("/build")
    public String buildChampionLib(Model model) {
        return resultPage(model, versionAction.buildChampionLib());
    }
}
