package wsg.lol.common.pojo.dto.state.item;

import wsg.lol.common.pojo.base.BaseDto;
import wsg.lol.common.pojo.dto.state.ItemDto;

import java.util.List;

/**
 * wsg
 *
 * @author EastSunrise
 */
public class ItemExtDto extends BaseDto {

    private List<ItemDto> itemDtoList;
    private List<GroupDto> groupDtoList;
    private List<TreeDto> treeDtoList;

    public List<ItemDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(List<ItemDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }

    public List<GroupDto> getGroupDtoList() {
        return groupDtoList;
    }

    public void setGroupDtoList(List<GroupDto> groupDtoList) {
        this.groupDtoList = groupDtoList;
    }

    public List<TreeDto> getTreeDtoList() {
        return treeDtoList;
    }

    public void setTreeDtoList(List<TreeDto> treeDtoList) {
        this.treeDtoList = treeDtoList;
    }
}
