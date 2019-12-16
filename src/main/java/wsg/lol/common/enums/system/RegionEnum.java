package wsg.lol.common.enums.system;

import wsg.lol.common.pojo.serialize.EqualsToSerializable;

import java.util.Locale;

/**
 * Enums for routes of platforms.
 *
 * @author Kingen
 * @see <a href="https://developer.riotgames.com/docs/lol#_routing-values">Routing Values</a>
 */
public enum RegionEnum implements EqualsToSerializable<String> {
    LOL,
    BR("br1.api.riotgames.com", "BR1", AvailableLocale.BRAZIL_PORTUGUESE),
    EUNE("eun1.api.riotgames.com", "EUN1",
            Locale.UK, AvailableLocale.CZECH, AvailableLocale.GREEK, AvailableLocale.HUNGARIAN, AvailableLocale.POLISH, AvailableLocale.ROMANIAN
    ),
    EUW("euw1.api.riotgames.com", "EUW1",
            Locale.UK, Locale.GERMANY, AvailableLocale.SPAIN, Locale.FRANCE, Locale.ITALY
    ),
    JP("jp1.api.riotgames.com", "JP1", Locale.JAPAN),
    KR("kr.api.riotgames.com", "KR", Locale.KOREA),
    LA1("la1.api.riotgames.com", "LA1", AvailableLocale.MEXICO_SPANISH),
    LA2("la2.api.riotgames.com", "LA2", AvailableLocale.MEXICO_SPANISH),
    NA("na1.api.riotgames.com", "NA1", Locale.US),
    OC1("oc1.api.riotgames.com", "OC1", AvailableLocale.AUSTRALIA_ENGLISH),
    RU("ru.api.riotgames.com", "RU", AvailableLocale.RUSSIA),
    TR("tr1.api.riotgames.com", "TR1", AvailableLocale.TURKEY),
    ;

    private String host;

    private String platformId;

    // the first is the default.
    private Locale[] locales;

    RegionEnum() {
    }

    RegionEnum(String host, String platformId, Locale... locales) {
        this.host = host;
        this.platformId = platformId;
        this.locales = locales;
    }

    public String getHost() {
        return host;
    }

    @Override
    public boolean equalsToObject(String s) {
        if (LOL.equals(this)) {
            return false;
        }

        return this.platformId.equalsIgnoreCase(s) || this.name().equalsIgnoreCase(s);
    }

    public Locale[] getLocales() {
        return locales;
    }

    private static class AvailableLocale {
        private static final Locale BRAZIL_PORTUGUESE = new Locale("pt", "BR");
        private static final Locale CZECH = new Locale("cs", "CZ");
        private static final Locale GREEK = new Locale("el", "GR");
        private static final Locale HUNGARIAN = new Locale("hu", "HU");
        private static final Locale POLISH = new Locale("pl", "PL");
        private static final Locale ROMANIAN = new Locale("ro", "RO");
        private static final Locale SPAIN = new Locale("es", "ES");
        private static final Locale MEXICO_SPANISH = new Locale("es", "MX");
        private static final Locale AUSTRALIA_ENGLISH = new Locale("en", "AU");
        private static final Locale RUSSIA = new Locale("ru", "RU");
        private static final Locale TURKEY = new Locale("tr", "TR");
    }
}
