package wsg.lol.pojo.dto.api.match;

import wsg.lol.pojo.base.ApiBean;

import java.util.List;

/**
 * @author King
 */
public class MatchListDto extends ApiBean {

    private List<MatchReferenceDto> matches;
    private int totalGames;
    private int startIndex;
    private int endIndex;

    public List<MatchReferenceDto> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchReferenceDto> matches) {
        this.matches = matches;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
