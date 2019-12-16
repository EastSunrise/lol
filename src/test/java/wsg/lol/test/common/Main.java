package wsg.lol.test.common;

import org.apache.commons.io.FileUtils;
import wsg.lol.common.pojo.dto.share.FeaturedGames;
import wsg.lol.common.pojo.serialize.FastJsonRedisSerializer;
import wsg.lol.config.CustomParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Main.
 *
 * @author Kingen
 */
public class Main {

    private static String path = "D:\\lol\\api\\jp1.api.riotgames.com\\lol\\spectator\\v4\\featured-games.json";

    private static FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);

    static {
        new CustomParser();
    }

    public static void main(String[] args) throws IOException {
        String text = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        FeaturedGames featuredGames = CustomParser.parseObject(text, FeaturedGames.class);
        byte[] bytes = serializer.serialize(featuredGames);
        Object object = serializer.deserialize(bytes);
        System.out.println(object);
    }
}
