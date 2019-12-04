package wsg.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wsg.lol.common.pojo.dto.summoner.SummonerDto;
import wsg.lol.service.intf.SummonerService;

/**
 * todo
 *
 * @author Kingen
 */
@CrossOrigin
@RestController
@RequestMapping("lol/summoner")
public class SummonerController {

    private SummonerService summonerService;

    @GetMapping
    public SummonerDto getScore(String name) {
        return summonerService.getSummonersByName(name);
    }

    @Autowired
    public void setSummonerService(SummonerService summonerService) {
        this.summonerService = summonerService;
    }
}
