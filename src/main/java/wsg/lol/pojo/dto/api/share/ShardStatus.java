package wsg.lol.pojo.dto.api.share;

import wsg.lol.pojo.base.ApiBean;

import java.util.List;

/**
 * @author King
 */
public class ShardStatus extends ApiBean {

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

    /**
     * @author King
     */
    public static class Service extends ApiBean {

        private String status;
        private List<Incident> incidents;
        private String name;
        private String slug;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Incident> getIncidents() {
            return incidents;
        }

        public void setIncidents(List<Incident> incidents) {
            this.incidents = incidents;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        /**
         * @author King
         */
        public static class Incident extends ApiBean {

            private boolean active;
            private String createdAt;
            private long id;
            private List<Message> updates;

            public boolean isActive() {
                return active;
            }

            public void setActive(boolean active) {
                this.active = active;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public List<Message> getUpdates() {
                return updates;
            }

            public void setUpdates(List<Message> updates) {
                this.updates = updates;
            }

            /**
             * @author King
             */
            public static class Message extends ApiBean {

                private String severity;
                private String author;
                private String createdAt;
                private List<Translation> translations;
                private String updatedAt;
                private String content;
                private String id;

                public String getSeverity() {
                    return severity;
                }

                public void setSeverity(String severity) {
                    this.severity = severity;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public List<Translation> getTranslations() {
                    return translations;
                }

                public void setTranslations(List<Translation> translations) {
                    this.translations = translations;
                }

                public String getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(String updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                /**
                 * @author King
                 */
                public static class Translation extends ApiBean {

                    private String locale;
                    private String content;
                    private String updatedAt;

                    public String getLocale() {
                        return locale;
                    }

                    public void setLocale(String locale) {
                        this.locale = locale;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getUpdatedAt() {
                        return updatedAt;
                    }

                    public void setUpdatedAt(String updatedAt) {
                        this.updatedAt = updatedAt;
                    }
                }
            }
        }
    }
}
