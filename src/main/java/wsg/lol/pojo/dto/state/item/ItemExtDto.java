package wsg.lol.pojo.dto.state.item;

import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.dto.state.ItemDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
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
