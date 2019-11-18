package wsg.lol.dao.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;

import javax.validation.constraints.NotNull;

/**
 * Custom handler for {@link Double} array.
 *
 * @author Kingen
 */
@MappedTypes({
        Double[].class
})
public class DoubleTypeHandler extends AbstractArrayTypeHandler<Double> {

    public DoubleTypeHandler(Class<Double> clazz) {
        super(clazz);
    }

    @Override
    protected String toString(@NotNull Double aDouble) {
        return aDouble.toString();
    }

    @Override
    protected Double parseObject(@NotNull String string) {
        return Double.parseDouble(string);
    }
}
