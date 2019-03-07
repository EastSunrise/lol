package wsg.lol.dto.api.match;

import java.util.List;

/**
 * @author King
 * @date 2019/2/12
 */
public class MatchTimelineDto {

    private List<MatchFrameDto> frames;
    private long frameInterval;

    public List<MatchFrameDto> getFrames() {
        return frames;
    }

    public void setFrames(List<MatchFrameDto> frames) {
        this.frames = frames;
    }

    public long getFrameInterval() {
        return frameInterval;
    }

    public void setFrameInterval(long frameInterval) {
        this.frameInterval = frameInterval;
    }
}
