package wsg.lol.test.common;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.config.ApiIdentifier;
import wsg.lol.dao.api.client.ApiClient;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.dao.mybatis.config.DatabaseIdentifier;
import wsg.lol.test.base.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Test to initialize the data.
 *
 * @author Kingen
 */
public class InitialTest extends BaseTest {

    private Map<RegionEnum, Integer[]> map = new HashMap<>();

    @Autowired
    private LeagueV4 leagueV4;

    @Autowired
    private ApiClient apiClient;

    @Before
    public void setUp() {
        map.put(RegionEnum.JP, new Integer[]{
                1, 1, 1, 1, 2, 3, 5, 5, 5, 5, 10, 5, 8, 10, 20, 9, 10, 9, 10, 7, 5, 3, 3, 2, 2, 1, 1
        });
        map.put(RegionEnum.KR, new Integer[]{
                2, 4, 10, 10, 20, 20, 50, 30, 30, 50, 100, 50, 70, 70, 100, 60, 70, 60, 70, 50, 50, 30, 30, 20, 10, 10, 4
        });
        map.put(RegionEnum.NA, new Integer[]{
                2, 4, 5, 10, 10, 20, 30, 30, 30, 30, 50, 40, 50, 50, 100, 40, 50, 40, 50, 40, 30, 30, 20, 20, 10, 5, 4
        });

    }

    @Test
    public void initialize() {
        for (Map.Entry<RegionEnum, Integer[]> entry : map.entrySet()) {
            List<String> summoners = new LinkedList<>();
            DatabaseIdentifier.setPlatform(entry.getKey());
            Integer[] pages = entry.getValue();
            int index = 0;
            for (TierEnum tier : TierEnum.RANKED_TIERS) {
                for (DivisionEnum division : DivisionEnum.validDivisions(tier)) {
                    int max = pages[index++];
                    for (int page = 1; page < max; page++) {
                        String username = apiClient.peekUsername();
                        ApiIdentifier.setApi(username);
                        List<LeagueEntryDto> leagueEntryDtoList = leagueV4.getLeagueEntriesExp(RankQueueEnum.RANKED_SOLO_5x5, tier, division, page);
                        if (leagueEntryDtoList == null || leagueEntryDtoList.isEmpty())
                            break;
                        for (LeagueEntryDto leagueEntryDto : leagueEntryDtoList) {
                            summoners.add(leagueEntryDto.getSummonerId() + "," + username + "," + leagueEntryDto.getSummonerName());
                        }
                    }
                }
            }
            try {
                FileUtils.writeLines(new File("D:/" + entry.getKey() + ".csv"), summoners);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
