package wsg.lol.common.pojo.dto.match;

import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 * @author EastSunrise
 */
public class MatchListDto extends BaseDto implements IJson {

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
