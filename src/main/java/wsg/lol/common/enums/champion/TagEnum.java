package wsg.lol.common.enums.champion;

/**
 * // TODO: (wangsigen, 2019/11/8)
 *
 * @author wangsigen
 * @date 2019/11/8
 * @since 1.0
 */
public enum TagEnum {
    Fighter(0, "战士"),
    Tank(1, "坦克");

    private int code;
    private String title;

    TagEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
