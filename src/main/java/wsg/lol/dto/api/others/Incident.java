package wsg.lol.dto.api.others;

import java.util.List;

/**
 * @author King
 * @date 2019/2/11
 */
public class Incident {

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
