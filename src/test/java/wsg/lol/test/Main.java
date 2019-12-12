package wsg.lol.test;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Main.
 *
 * @author Kingen
 */
public class Main {

    public static void main(String[] args) {
        String path = "D:/test.json";
        String json = null;
        try {
            json = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Champion champion = JSON.parseObject(json, Champion.class);
        System.out.println(champion);
    }

    @Data
    private static class Champion {
        private String name;

        private Integer imageX;
    }
}
