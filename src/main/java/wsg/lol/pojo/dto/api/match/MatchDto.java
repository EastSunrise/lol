package wsg.lol.pojo.dto.api.match;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wsg.lol.common.utils.BeanUtil;
import wsg.lol.pojo.base.IJSONTransfer;
import wsg.lol.pojo.base.StateBean;
import wsg.lol.pojo.dmo.match.MatchDmo;
import wsg.lol.pojo.dmo.match.ParticipantDmo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/12
 */
public class MatchDto extends StateBean implements IJSONTransfer {

    private MatchDmo matchDmo;

    private List<ParticipantDmo> participantDmoList;

    @Override
    public void parseFromJSONObject(JSONObject match) {
        MatchDmo matchDmo = JSON.parseObject(match.toJSONString(), MatchDmo.class);
        long gameId = matchDmo.getGameId();
        setMatchDmo(matchDmo);

        Map<Integer, String> map = new HashMap<>();
        for (Object object : match.getJSONArray("participantIdentities")) {
            JSONObject participantIdentity = (JSONObject) object;
            map.put(participantIdentity.getInteger("participantId"),
                    participantIdentity.getJSONObject("player").getString("summonerId"));
        }

        List<ParticipantDmo> participantDmoList = new LinkedList<>();
        for (Object object : match.getJSONArray("participants")) {
            JSONObject participant = (JSONObject) object;
            ParticipantDmo participantDmo = JSON.toJavaObject(participant, ParticipantDmo.class);
            BeanUtil.parseFromJSONObject(participantDmo, participant);
            participantDmo.setGameId(gameId);
            participantDmo.setSummonerId(map.get(participantDmo.getParticipantId()));
            participantDmoList.add(participantDmo);
        }
        setParticipantDmoList(participantDmoList);
    }

    public MatchDmo getMatchDmo() {
        return matchDmo;
    }

    public void setMatchDmo(MatchDmo matchDmo) {
        this.matchDmo = matchDmo;
    }

    public List<ParticipantDmo> getParticipantDmoList() {
        return participantDmoList;
    }

    public void setParticipantDmoList(List<ParticipantDmo> participantDmoList) {
        this.participantDmoList = participantDmoList;
    }
}
