package wsg.lol.test.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.common.enums.share.RankQueueEnum;
import wsg.lol.common.enums.summoner.DivisionEnum;
import wsg.lol.common.enums.summoner.TierEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.pojo.dto.summoner.LeagueEntryDto;
import wsg.lol.config.ApiIdentifier;
import wsg.lol.config.DragonConfig;
import wsg.lol.config.GlobalConfig;
import wsg.lol.dao.api.client.ApiClient;
import wsg.lol.dao.api.impl.LeagueV4;
import wsg.lol.test.base.BaseTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    private static final String TABLE_NAME = "t_event_summoner";
    private static Map<RegionEnum, Integer[]> map = new HashMap<>();
    @Autowired
    private LeagueV4 leagueV4;

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private DragonConfig dragonConfig;

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
        List<String> summoners = new LinkedList<>();
        Integer[] pages = map.get(globalConfig.getRegion());
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
            FileUtils.writeLines(new File(getPath(globalConfig.getRegion())), summoners);
            csv2Sql(globalConfig.getRegion());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void csv2Sql(RegionEnum region) throws IOException {
        List<String> lines = FileUtils.readLines(new File(getPath(region)), StandardCharsets.UTF_8);
        StringBuilder sql = new StringBuilder();
        String insertTable = "insert into " + region + "." + TABLE_NAME;
        int count = 0;
        for (String line : lines) {
            if (count++ % 1000 == 0) {
                sql.append(";\n").append(insertTable).append(" values\n");
            } else {
                sql.append(",\n");
            }
            String[] parts = line.split(",");
            sql.append("('").append(parts[0]).append("',0,'").append(parts[1]).append("','2019-11-21 00:00:00',null)");
        }
        sql.append(";");
        FileUtils.write(new File(toPath(region)), sql, StandardCharsets.UTF_8);
    }

    private String toPath(RegionEnum region) {
        return StringUtils.joinWith("DB", "01_lol_1.0", "lol", "SYS_INIT" + region + ".sql");
    }

    private String getPath(RegionEnum region) {
        return StringUtils.joinWith(File.separator, dragonConfig.getDirectory(), "lib", region + ".csv");
    }
}
