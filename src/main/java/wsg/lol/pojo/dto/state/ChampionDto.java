package wsg.lol.pojo.dto.state;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import wsg.lol.pojo.base.BaseDto;
import wsg.lol.pojo.base.IJson;
import wsg.lol.pojo.dto.state.others.ImageDto;
import wsg.lol.pojo.dto.state.spell.ChampionSpellDto;

import java.util.List;

/**
 * wsg
 *
 * @author wangsigen
 */
@Document
public class ChampionDto extends BaseDto implements IJson {

    @Id
    private String key;

    @Indexed(unique = true)
    private String id;

    @Indexed
    private String name;

    private String title;
    private ImageDto image;
    private String lore;
    private String blurb;
    private String partype;
    private InfoAttr info;
    private StatAttr stats;
    private PassiveDto passive;
    private List<SkinDto> skins;
    private List<String> allytips;
    private List<String> enemytips;
    private List<String> tags;
    private List<ChampionSpellDto> spells;
    private List<RecommendedDto> recommended;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public InfoAttr getInfo() {
        return info;
    }

    public void setInfo(InfoAttr info) {
        this.info = info;
    }

    public StatAttr getStats() {
        return stats;
    }

    public void setStats(StatAttr stats) {
        this.stats = stats;
    }

    public PassiveDto getPassive() {
        return passive;
    }

    public void setPassive(PassiveDto passive) {
        this.passive = passive;
    }

    public List<SkinDto> getSkins() {
        return skins;
    }

    public void setSkins(List<SkinDto> skins) {
        this.skins = skins;
    }

    public List<String> getAllytips() {
        return allytips;
    }

    public void setAllytips(List<String> allytips) {
        this.allytips = allytips;
    }

    public List<String> getEnemytips() {
        return enemytips;
    }

    public void setEnemytips(List<String> enemytips) {
        this.enemytips = enemytips;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<ChampionSpellDto> getSpells() {
        return spells;
    }

    public void setSpells(List<ChampionSpellDto> spells) {
        this.spells = spells;
    }

    public List<RecommendedDto> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<RecommendedDto> recommended) {
        this.recommended = recommended;
    }

    /**
     * wsg
     *
     * @author wangsigen
     */
    public static class InfoAttr extends BaseDto {

        private int attack;
        private int defense;
        private int magic;
        private int difficulty;

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public int getDefense() {
            return defense;
        }

        public void setDefense(int defense) {
            this.defense = defense;
        }

        public int getMagic() {
            return magic;
        }

        public void setMagic(int magic) {
            this.magic = magic;
        }

        public int getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }
    }

    /**
     * wsg
     *
     * @author wangsigen
     */
    public static class PassiveDto extends BaseDto {

        private String name;
        private String description;
        private ImageDto image;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ImageDto getImage() {
            return image;
        }

        public void setImage(ImageDto image) {
            this.image = image;
        }
    }

    /**
     * wsg
     *
     * @author wangsigen
     */
    public static class SkinDto extends BaseDto {

        private String id;
        private int num;
        private String name;
        private boolean chromas;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isChromas() {
            return chromas;
        }

        public void setChromas(boolean chromas) {
            this.chromas = chromas;
        }
    }

    /**
     * wsg
     *
     * @author wangsigen
     */
    public static class StatAttr extends BaseDto {

        private int hp;
        private int hpperlevel;
        private int mp;
        private int mpperlevel;
        private int movespeed;
        private int armor;
        private double armorperlevel;
        private double spellblock;
        private double spellblockperlevel;
        private int attackrange;
        private int hpregen;
        private double hpregenperlevel;
        private int mpregen;
        private int mpregenperlevel;
        private int crit;
        private int critperlevel;
        private int attackdamage;
        private int attackdamageperlevel;
        private double attackspeedperlevel;
        private double attackspeed;

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getHpperlevel() {
            return hpperlevel;
        }

        public void setHpperlevel(int hpperlevel) {
            this.hpperlevel = hpperlevel;
        }

        public int getMp() {
            return mp;
        }

        public void setMp(int mp) {
            this.mp = mp;
        }

        public int getMpperlevel() {
            return mpperlevel;
        }

        public void setMpperlevel(int mpperlevel) {
            this.mpperlevel = mpperlevel;
        }

        public int getMovespeed() {
            return movespeed;
        }

        public void setMovespeed(int movespeed) {
            this.movespeed = movespeed;
        }

        public int getArmor() {
            return armor;
        }

        public void setArmor(int armor) {
            this.armor = armor;
        }

        public double getArmorperlevel() {
            return armorperlevel;
        }

        public void setArmorperlevel(double armorperlevel) {
            this.armorperlevel = armorperlevel;
        }

        public double getSpellblock() {
            return spellblock;
        }

        public void setSpellblock(double spellblock) {
            this.spellblock = spellblock;
        }

        public double getSpellblockperlevel() {
            return spellblockperlevel;
        }

        public void setSpellblockperlevel(double spellblockperlevel) {
            this.spellblockperlevel = spellblockperlevel;
        }

        public int getAttackrange() {
            return attackrange;
        }

        public void setAttackrange(int attackrange) {
            this.attackrange = attackrange;
        }

        public int getHpregen() {
            return hpregen;
        }

        public void setHpregen(int hpregen) {
            this.hpregen = hpregen;
        }

        public double getHpregenperlevel() {
            return hpregenperlevel;
        }

        public void setHpregenperlevel(double hpregenperlevel) {
            this.hpregenperlevel = hpregenperlevel;
        }

        public int getMpregen() {
            return mpregen;
        }

        public void setMpregen(int mpregen) {
            this.mpregen = mpregen;
        }

        public int getMpregenperlevel() {
            return mpregenperlevel;
        }

        public void setMpregenperlevel(int mpregenperlevel) {
            this.mpregenperlevel = mpregenperlevel;
        }

        public int getCrit() {
            return crit;
        }

        public void setCrit(int crit) {
            this.crit = crit;
        }

        public int getCritperlevel() {
            return critperlevel;
        }

        public void setCritperlevel(int critperlevel) {
            this.critperlevel = critperlevel;
        }

        public int getAttackdamage() {
            return attackdamage;
        }

        public void setAttackdamage(int attackdamage) {
            this.attackdamage = attackdamage;
        }

        public int getAttackdamageperlevel() {
            return attackdamageperlevel;
        }

        public void setAttackdamageperlevel(int attackdamageperlevel) {
            this.attackdamageperlevel = attackdamageperlevel;
        }

        public double getAttackspeedperlevel() {
            return attackspeedperlevel;
        }

        public void setAttackspeedperlevel(double attackspeedperlevel) {
            this.attackspeedperlevel = attackspeedperlevel;
        }

        public double getAttackspeed() {
            return attackspeed;
        }

        public void setAttackspeed(double attackspeed) {
            this.attackspeed = attackspeed;
        }
    }

    /**
     * wsg
     *
     * @author wangsigen
     */
    public static class RecommendedDto extends BaseDto {

        private String champion;
        private String title;
        private String map;
        private String mode;
        private String type;
        private String customTag;
        private int sortrank;
        private boolean extensionPage;
        private boolean useObviousCheckmark;
        private Object customPanel;
        private List<BlockDto> blocks;

        public String getChampion() {
            return champion;
        }

        public void setChampion(String champion) {
            this.champion = champion;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCustomTag() {
            return customTag;
        }

        public void setCustomTag(String customTag) {
            this.customTag = customTag;
        }

        public int getSortrank() {
            return sortrank;
        }

        public void setSortrank(int sortrank) {
            this.sortrank = sortrank;
        }

        public boolean isExtensionPage() {
            return extensionPage;
        }

        public void setExtensionPage(boolean extensionPage) {
            this.extensionPage = extensionPage;
        }

        public boolean isUseObviousCheckmark() {
            return useObviousCheckmark;
        }

        public void setUseObviousCheckmark(boolean useObviousCheckmark) {
            this.useObviousCheckmark = useObviousCheckmark;
        }

        public Object getCustomPanel() {
            return customPanel;
        }

        public void setCustomPanel(Object customPanel) {
            this.customPanel = customPanel;
        }

        public List<BlockDto> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<BlockDto> blocks) {
            this.blocks = blocks;
        }

        /**
         * wsg
         *
         * @author wangsigen
         */
        public static class BlockDto extends BaseDto {

            private String type;
            private boolean recMath;
            private boolean recSteps;
            private int minSummonerLevel;
            private int maxSummonerLevel;
            private String showIfSummonerSpell;
            private String hideIfSummonerSpell;
            private String appendAfterSection;
            private List<String> visibleWithAllOf;
            private List<String> hiddenWithAnyOf;
            private List<ItemAttr> items;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isRecMath() {
                return recMath;
            }

            public void setRecMath(boolean recMath) {
                this.recMath = recMath;
            }

            public boolean isRecSteps() {
                return recSteps;
            }

            public void setRecSteps(boolean recSteps) {
                this.recSteps = recSteps;
            }

            public int getMinSummonerLevel() {
                return minSummonerLevel;
            }

            public void setMinSummonerLevel(int minSummonerLevel) {
                this.minSummonerLevel = minSummonerLevel;
            }

            public int getMaxSummonerLevel() {
                return maxSummonerLevel;
            }

            public void setMaxSummonerLevel(int maxSummonerLevel) {
                this.maxSummonerLevel = maxSummonerLevel;
            }

            public String getShowIfSummonerSpell() {
                return showIfSummonerSpell;
            }

            public void setShowIfSummonerSpell(String showIfSummonerSpell) {
                this.showIfSummonerSpell = showIfSummonerSpell;
            }

            public String getHideIfSummonerSpell() {
                return hideIfSummonerSpell;
            }

            public void setHideIfSummonerSpell(String hideIfSummonerSpell) {
                this.hideIfSummonerSpell = hideIfSummonerSpell;
            }

            public String getAppendAfterSection() {
                return appendAfterSection;
            }

            public void setAppendAfterSection(String appendAfterSection) {
                this.appendAfterSection = appendAfterSection;
            }

            public List<String> getVisibleWithAllOf() {
                return visibleWithAllOf;
            }

            public void setVisibleWithAllOf(List<String> visibleWithAllOf) {
                this.visibleWithAllOf = visibleWithAllOf;
            }

            public List<String> getHiddenWithAnyOf() {
                return hiddenWithAnyOf;
            }

            public void setHiddenWithAnyOf(List<String> hiddenWithAnyOf) {
                this.hiddenWithAnyOf = hiddenWithAnyOf;
            }

            public List<ItemAttr> getItems() {
                return items;
            }

            public void setItems(List<ItemAttr> items) {
                this.items = items;
            }

            /**
             * wsg
             *
             * @author wangsigen
             */
            public static class ItemAttr extends BaseDto {

                private String id;
                private int count;
                private boolean hideCount;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public boolean isHideCount() {
                    return hideCount;
                }

                public void setHideCount(boolean hideCount) {
                    this.hideCount = hideCount;
                }
            }
        }
    }
}
