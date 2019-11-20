package wsg.lol.common.pojo.dto.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import wsg.lol.common.base.BaseDto;

/**
 * Bean for stats of items.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemStatsDto extends BaseDto {

    private Integer itemId;

    private Integer FlatHPPoolMod;
    private Integer FlatMPPoolMod;
    private Double FlatHPRegenMod;
    private Integer FlatArmorMod;
    private Integer FlatPhysicalDamageMod;
    private Integer FlatMagicDamageMod;
    private Integer FlatMovementSpeedMod;
    private Double PercentMovementSpeedMod;
    private Double PercentAttackSpeedMod;
    private Double FlatCritChanceMod;
    private Integer FlatSpellBlockMod;
    private Double PercentLifeStealMod;

    public static void main(String[] args) {
        String str = "    private Integer FlatHPPoolMod;\n" +
                "    private Integer FlatMPPoolMod;\n" +
                "    private Double FlatHPRegenMod;\n" +
                "    private Integer FlatArmorMod;\n" +
                "    private Integer FlatPhysicalDamageMod;\n" +
                "    private Integer FlatMagicDamageMod;\n" +
                "    private Integer FlatMovementSpeedMod;\n" +
                "    private Double PercentMovementSpeedMod;\n" +
                "    private Double PercentAttackSpeedMod;\n" +
                "    private Double FlatCritChanceMod;\n" +
                "    private Integer FlatSpellBlockMod;\n" +
                "    private Double PercentLifeStealMod;";
        str = StringUtils.replace(StringUtils.strip(str), "private Integer", "");
        str = StringUtils.replace(str, "private Double", "");
        String[] fields = StringUtils.split(str, ";");
        for (String field : fields) {
            System.out.println(String.format("#{item.%s},", StringUtils.strip(field)));
        }

    }
}
