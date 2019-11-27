package wsg.lol.common.pojo.dto.share;

/**
 * Bean for extension of items.
 *
 * @author EastSunrise
 */
public class ItemExtDto extends ItemDto {

    private ImageDto image;
    private ItemStatsDto stats;

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public ItemStatsDto getStats() {
        return stats;
    }

    public void setStats(ItemStatsDto stats) {
        this.stats = stats;
    }
}
