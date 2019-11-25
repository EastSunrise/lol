package wsg.lol.dao.mybatis.config;

/**
 * Strategy interface for updating the static data.
 *
 * @author Kingen
 */
public interface ClearMapper<T> {
    int clear();
}
