package wsg.lol.common.pojo.dto.share;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;
import wsg.lol.common.constant.JSONConst;
import wsg.lol.common.enums.share.MessageSeverityEnum;
import wsg.lol.common.enums.share.ServiceEnum;
import wsg.lol.common.enums.share.ServiceStatusEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * DTO for shared status.
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

    @Data
    private static class Service implements Serializable {

        private ServiceEnum name;

        private ServiceStatusEnum status;

        private List<Incident> incidents;

        private String slug;

        @Data
        private static class Incident implements Serializable {

            private Boolean active;
            private Long id;
            private List<Message> updates;

            @JSONField(format = JSONConst.DATE_FORMAT)
            private Date createdAt;

            @Data
            private static class Message implements Serializable {

                private String id;
                private String author;
                private String content;
                private String heading;

                private MessageSeverityEnum severity;

                private List<Translation> translations;

                @JSONField(format = JSONConst.DATE_FORMAT)
                private Date createdAt;

                @JSONField(format = JSONConst.DATE_FORMAT)
                private Date updatedAt;

                @Data
                private static class Translation implements Serializable {

                    private Locale locale;
                    private String content;

                    @JSONField(format = JSONConst.DATE_FORMAT)
                    private Date updatedAt;
                }
            }
        }
    }
}
