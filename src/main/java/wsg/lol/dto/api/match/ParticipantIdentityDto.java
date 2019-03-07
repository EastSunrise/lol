package wsg.lol.dto.api.match;

/**
 * @author King
 * @date 2019/2/12
 */
public class ParticipantIdentityDto {

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
