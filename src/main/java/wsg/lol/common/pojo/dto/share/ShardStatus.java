package wsg.lol.common.pojo.dto.share;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.List;
import java.util.Locale;

/**
 * Bean for shared status.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShardStatus extends BaseDto {

    private String name;
    private String regionTag;
    private String hostname;
    private List<Service> services;
    private String slug;
    private List<Locale> locales;
}
