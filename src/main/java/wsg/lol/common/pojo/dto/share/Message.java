package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.enums.shared.MessageSeverityEnum;
import wsg.lol.common.pojo.parser.CustomEnumDeserializer;

import java.util.Date;
import java.util.List;

/**
 * Bean for message.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Message extends BaseDto {

    private String id;
    private String author;
    private String content;
    private String heading;

    @JSONField(deserializeUsing = CustomEnumDeserializer.class)
    private MessageSeverityEnum severity;

    private List<Translation> translations;

    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt;
    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedAt;
}
