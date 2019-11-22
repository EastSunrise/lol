package wsg.lol.common;

public class LeagueEntryKey {
    private String summonerId;

    private Byte queueType;

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId == null ? null : summonerId.trim();
    }

    public Byte getQueueType() {
        return queueType;
    }

    public void setQueueType(Byte queueType) {
        this.queueType = queueType;
    }
}