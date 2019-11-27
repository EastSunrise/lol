package wsg.lol.common;

public class ItemDo {
    private Integer id;

    private String name;

    private String description;

    private String colloq;

    private String plaintext;

    private String from;

    private String into;

    private Integer goldBase;

    private Byte goldPurchasable;

    private Integer goldTotal;

    private Integer goldSell;

    private String tags;

    private String maps;

    private String effect;

    private Integer depth;

    private Byte consumed;

    private Byte consumeOnFull;

    private Byte inStore;

    private Byte hideFromAll;

    private Integer stacks;

    private String requiredChampion;

    private Integer specialRecipe;

    private String requiredAlly;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getColloq() {
        return colloq;
    }

    public void setColloq(String colloq) {
        this.colloq = colloq == null ? null : colloq.trim();
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext == null ? null : plaintext.trim();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }

    public String getInto() {
        return into;
    }

    public void setInto(String into) {
        this.into = into == null ? null : into.trim();
    }

    public Integer getGoldBase() {
        return goldBase;
    }

    public void setGoldBase(Integer goldBase) {
        this.goldBase = goldBase;
    }

    public Byte getGoldPurchasable() {
        return goldPurchasable;
    }

    public void setGoldPurchasable(Byte goldPurchasable) {
        this.goldPurchasable = goldPurchasable;
    }

    public Integer getGoldTotal() {
        return goldTotal;
    }

    public void setGoldTotal(Integer goldTotal) {
        this.goldTotal = goldTotal;
    }

    public Integer getGoldSell() {
        return goldSell;
    }

    public void setGoldSell(Integer goldSell) {
        this.goldSell = goldSell;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps == null ? null : maps.trim();
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect == null ? null : effect.trim();
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Byte getConsumed() {
        return consumed;
    }

    public void setConsumed(Byte consumed) {
        this.consumed = consumed;
    }

    public Byte getConsumeOnFull() {
        return consumeOnFull;
    }

    public void setConsumeOnFull(Byte consumeOnFull) {
        this.consumeOnFull = consumeOnFull;
    }

    public Byte getInStore() {
        return inStore;
    }

    public void setInStore(Byte inStore) {
        this.inStore = inStore;
    }

    public Byte getHideFromAll() {
        return hideFromAll;
    }

    public void setHideFromAll(Byte hideFromAll) {
        this.hideFromAll = hideFromAll;
    }

    public Integer getStacks() {
        return stacks;
    }

    public void setStacks(Integer stacks) {
        this.stacks = stacks;
    }

    public String getRequiredChampion() {
        return requiredChampion;
    }

    public void setRequiredChampion(String requiredChampion) {
        this.requiredChampion = requiredChampion == null ? null : requiredChampion.trim();
    }

    public Integer getSpecialRecipe() {
        return specialRecipe;
    }

    public void setSpecialRecipe(Integer specialRecipe) {
        this.specialRecipe = specialRecipe;
    }

    public String getRequiredAlly() {
        return requiredAlly;
    }

    public void setRequiredAlly(String requiredAlly) {
        this.requiredAlly = requiredAlly == null ? null : requiredAlly.trim();
    }
}