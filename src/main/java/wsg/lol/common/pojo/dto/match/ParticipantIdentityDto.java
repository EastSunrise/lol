package wsg.lol.common.pojo.dto.match;

import wsg.lol.common.pojo.base.BaseDto;

/**
 * @author EastSunrise
 */
public class ParticipantIdentityDto extends BaseDto {

    /**
     * Player information.
     */
    private PlayerDto player;
    private int participantId;

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }
}
