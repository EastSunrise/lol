package wsg.lol.dao.mybatis.typehandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.MappedTypes;

import javax.validation.constraints.NotNull;

/**
 * Custom handler for two-dimensional {@link Integer} array.
 *
 * @author Kingen
 */
@MappedTypes({
        Integer[][].class
})
public class IntegerDoubleArrayTypeHandler extends AbstractArrayTypeHandler<Integer[]> {

    private static final String SUB_SEPARATOR = "/";

    public IntegerDoubleArrayTypeHandler(Class<Integer[]> clazz) {
        super(clazz);
    }

    @Override
    protected String toString(@NotNull Integer[] integers) {
        return StringUtils.join(integers, SUB_SEPARATOR);
    }

    @Override
    protected Integer[] parseObject(@NotNull String string) {
        String[] parts = string.split(SUB_SEPARATOR);
        Integer[] integers = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) {
            integers[i] = Integer.parseInt(parts[i]);
        }
        return integers;
    }
}
