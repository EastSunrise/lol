package wsg.lol.common.pojo.serialize;

/**
 * Serialize to json.
 *
 * @author Kingen
 */
public interface JSONSerializable<T> {

    T serialize();
}
