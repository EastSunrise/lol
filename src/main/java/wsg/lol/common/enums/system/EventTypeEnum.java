package wsg.lol.common.enums.system;

/**
 * Enum for types of events.
 *
 * @author Kingen
 */
public enum EventTypeEnum {
    SummonerId;

    public final String getEventBeanName() {
        return name() + "EventHandler";
    }
}
