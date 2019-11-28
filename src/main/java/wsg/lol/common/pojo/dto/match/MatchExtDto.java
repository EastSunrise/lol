package wsg.lol.common.pojo.dto.match;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * DTO for extension of a match.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MatchExtDto extends MatchDto {

    private List<ParticipantIdentityDto> participantIdentities;

    private List<TeamStatsDto> teams;

    private List<ParticipantDto> participants;
}
