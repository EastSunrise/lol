package wsg.lol.dto.api.match;

import wsg.lol.dmo.match.MatchDmo;

import java.util.List;

/**
 * @author King
 * @date 2019/2/12
 */
public class MatchListDto {

    private List<MatchDmo> matches;
    private int totalGames;
    private int startIndex;
    private int endIndex;

    public List<MatchDmo> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDmo> matches) {
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
