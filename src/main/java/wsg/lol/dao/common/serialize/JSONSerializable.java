package wsg.lol.dao.common.serialize;

/**
 * Serialize to json.
 *
 * @author Kingen
 */
public interface JSONSerializable<T> {

    T serialize();
}
