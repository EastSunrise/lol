package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.Date;
import java.util.List;

/**
 * Bean for incident.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Incident extends BaseDto {

    private Boolean active;
    private Long id;
    private List<Message> updates;

    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'T'")
    private Date createdAt;
}
