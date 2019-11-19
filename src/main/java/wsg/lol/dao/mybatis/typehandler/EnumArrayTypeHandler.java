package wsg.lol.dao.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;
import wsg.lol.common.enums.champion.ChampionTagEnum;
import wsg.lol.common.enums.champion.ItemTagEnum;
import wsg.lol.common.enums.game.GameModeEnum;
import wsg.lol.common.enums.game.MapEnum;
import wsg.lol.common.util.EnumUtils;

import javax.validation.constraints.NotNull;

/**
 * Custom handler between the {@link T} array and the string joining the {@link Enum#ordinal()} separated with {@link EnumArrayTypeHandler#getSeparator()}
 *
 * @author Kingen
 */
@MappedTypes({
        ChampionTagEnum[].class, ItemTagEnum[].class, MapEnum[].class, GameModeEnum[].class
})
public class EnumArrayTypeHandler<T extends Enum<T>> extends AbstractArrayTypeHandler<T> {

    public EnumArrayTypeHandler(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected String toString(@NotNull T t) {
        return String.valueOf(t.ordinal());
    }

    @Override
    protected T parseObject(@NotNull String string) {
        return EnumUtils.parseEnumByOrdinal(Integer.parseInt(string), clazz);
    }
}
