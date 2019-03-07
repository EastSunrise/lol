package wsg.lol.common.base;

import java.io.IOException;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2019-02-27 15:26
 */
public class ResultDto {
    public static ResultDto fail() {
        return null;
    }

    public static ResultDto fail(String errorMsg) {
        return null;
    }

    public static ResultDto success() {
        return null;
    }

    public static ResultDto fail(IOException e) {
        return null;
    }
}
