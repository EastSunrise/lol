package wsg.lol.pojo.dto.api.others;

import java.util.List;

/**
 * @author King
 * @date 2019/2/11
 */
public class ShardStatus {

    private String name;
    private String regionTag;
    private String hostname;
    private List<Service> services;
    private String slug;
    private List<String> locales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionTag() {
        return regionTag;
    }

    public void setRegionTag(String regionTag) {
        this.regionTag = regionTag;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getLocales() {
        return locales;
    }

    public void setLocales(List<String> locales) {
        this.locales = locales;
    }
}
