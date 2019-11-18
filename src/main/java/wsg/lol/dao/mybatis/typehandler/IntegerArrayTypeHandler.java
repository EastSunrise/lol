package wsg.lol.dao.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;

import javax.validation.constraints.NotNull;

/**
 * Custom handler for {@link Integer} array.
 *
 * @author Kingen
 */
@MappedTypes({
        Integer[].class
})
public class IntegerArrayTypeHandler extends AbstractArrayTypeHandler<Integer> {

    public IntegerArrayTypeHandler(Class<Integer> clazz) {
        super(clazz);
    }

    @Override
    protected String toString(@NotNull Integer integer) {
        return integer.toString();
    }

    @Override
    protected Integer parseObject(@NotNull String string) {
        return Integer.parseInt(string);
    }
}
