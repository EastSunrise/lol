package wsg.lol.pojo.dto.api.tournament;

import wsg.lol.common.annotation.Optional;
import wsg.lol.common.annotation.Required;
import wsg.lol.pojo.base.QueryDto;

/**
 * wsg
 *
 * @author wangsigen
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
