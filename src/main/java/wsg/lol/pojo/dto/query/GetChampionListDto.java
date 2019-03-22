package wsg.lol.pojo.dto.query;

import wsg.lol.pojo.base.QueryDto;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-03-20 11:04
 */
public class GetChampionListDto extends QueryDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
