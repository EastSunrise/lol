package wsg.lol.common;

public class ConfigDoKey {
    private String name;

    private Byte region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getRegion() {
        return region;
    }

    public void setRegion(Byte region) {
        this.region = region;
    }
}