package wsg.lol.common.pojo.dto.share;

import wsg.lol.common.base.BaseDto;

import java.util.List;

/**
 * // TODO: (Kingen, 2019/11/18)
 * @author EastSunrise
 */
public class Incident extends BaseDto {

    private boolean active;
    private String createdAt;
    private long id;
    private List<Message> updates;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Message> getUpdates() {
        return updates;
    }

    public void setUpdates(List<Message> updates) {
        this.updates = updates;
    }

}
