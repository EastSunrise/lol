package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

import java.util.Date;
import java.util.Locale;

/**
 * Bean for translation.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Translation extends BaseDto {

    private Locale locale;
    private String content;

    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedAt;
}
