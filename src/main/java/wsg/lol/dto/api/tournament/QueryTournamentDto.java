package wsg.lol.dto.api.tournament;

import wsg.lol.common.base.QueryDto;
import wsg.lol.common.constants.annotation.Optional;
import wsg.lol.common.constants.annotation.Required;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-01 11:52
 */
public class QueryTournamentDto extends QueryDto {

    /**
     * The number of codes to create (max 1000)
     */
    @Optional
    private Integer count;

    /**
     * The tournament ID
     */
    @Required
    private Long tournamentId;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
