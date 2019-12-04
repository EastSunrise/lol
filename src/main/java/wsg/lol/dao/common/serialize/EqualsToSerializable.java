package wsg.lol.dao.common.serialize;

/**
 * Serializable for alias of enum.
 *
 * @author Kingen
 */
public interface EqualsToSerializable<T> {

    boolean equalsToObject(T t);
}
