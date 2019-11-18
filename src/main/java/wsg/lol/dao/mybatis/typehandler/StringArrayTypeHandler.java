package wsg.lol.dao.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;

import javax.validation.constraints.NotNull;

/**
 * Custom handler for {@link String} array.
 *
 * @author Kingen
 */
@MappedTypes({
        String[].class
})
public class StringArrayTypeHandler extends AbstractArrayTypeHandler<String> {

    public StringArrayTypeHandler(Class<String> clazz) {
        super(clazz);
    }

    @Override
    protected String toString(@NotNull String s) {
        return s;
    }

    @Override
    protected String parseObject(@NotNull String string) {
        return string;
    }
}
