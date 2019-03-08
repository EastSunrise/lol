package wsg.lol.common.serializer;

import wsg.lol.common.constants.DefaultConfig;
import wsg.lol.data.api.MatchV4;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-08 09:53
 */
public class Test {

    public static void main(String[] args) {
        DefaultConfig.setApiKey("RGAPI-bc627a29-a9f1-4eb6-b5e8-08ca87476acf");
        MatchV4.getMatchById(2856655466L);
    }
}
