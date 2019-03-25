package wsg.lol.runner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import wsg.lol.pojo.base.BaseResult;
import wsg.lol.service.action.intf.RealAction;
import wsg.lol.service.action.intf.VersionAction;

/**
 * wsg
 *
 * @author wangsigen
 */
@Component
public class BuildRunner implements ApplicationRunner {

    @Autowired
    private VersionAction versionAction;

    @Autowired
    private RealAction realAction;

    @Override
    public void run(ApplicationArguments args) {
        BaseResult result;
        do {
            result = build();
        } while (!result.isSuccess());

        while (true) {
            extend();
        }
    }

    private BaseResult build() {
        versionAction.updateItemLib();
        versionAction.updateChampionLib();
        return realAction.buildBaseSummonerLibByLeague();
    }

    private void extend() {
        realAction.updateLeagues();
        realAction.extendSummonerLibByMatch();
    }
}
