package wsg.lol.controller;

/**
 * todo
 *
 * @author EastSunrise
 */
public abstract class BaseController {

    abstract String templatePath(String fileName);

    String redirect() {
        return "redirect:" + "/lol/summoner/index";
    }
}
