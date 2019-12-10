package wsg.lol.common.task;

import wsg.lol.common.base.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * For tasks of map arguments.
 *
 * @author Kingen
 */
public abstract class MapTaskStrategy<K, V, R extends Result> implements MinTaskStrategy<Map<K, V>, R> {

    @Override
    public int getLength(Map<K, V> map) {
        return map.size();
    }

    @Override
    public List<Map<K, V>> split(Map<K, V> map) {
        Map<K, V> l = new HashMap<>(), r = new HashMap<>();
        int count = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (count % 2 == 0) {
                l.put(entry.getKey(), entry.getValue());
            } else {
                r.put(entry.getKey(), entry.getValue());
            }
            count++;
        }
        return Arrays.asList(l, r);
    }
}
