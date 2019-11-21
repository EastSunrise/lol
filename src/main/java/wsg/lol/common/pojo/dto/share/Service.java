package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.shared.ServiceEnum;
import wsg.lol.common.enums.shared.ServiceStatusEnum;
import wsg.lol.common.pojo.parser.CustomEnumDeserializer;

import java.util.List;

/**
 * Bean for service of shared status.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Service extends BaseDto {

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private ServiceEnum name;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private ServiceStatusEnum status;

    private List<Incident> incidents;
    private String slug;
}
