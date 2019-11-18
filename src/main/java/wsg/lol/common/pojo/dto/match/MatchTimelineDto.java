package wsg.lol.common.pojo.dto.match;

import wsg.lol.common.base.BaseDto;
import wsg.lol.common.base.IJson;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 * @author EastSunrise
 */
public class MatchTimelineDto extends BaseDto implements IJson {

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
