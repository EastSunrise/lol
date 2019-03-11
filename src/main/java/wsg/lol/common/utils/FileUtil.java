package wsg.lol.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wsg.lol.common.base.BaseResult;

import java.io.*;

/**
 * @author King
 * @date 2019/2/13
 */
public class FileUtil {

    public static final String SYSTEM_SEPARATOR = "/";

    public static String concat2Path(String... args) {
        return StringUtil.join(args, SYSTEM_SEPARATOR);
    }

    public static BaseResult writeString2File(String str, String savePath) {
        LogUtil.info("Writing to: " + savePath);
        File saveFile = new File(savePath);
        File dir = saveFile.getParentFile();
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return BaseResult.fail("Fail to create directory.");
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            return BaseResult.fail(e);
        }
        return BaseResult.success();
    }

    public static JSONObject readJSONObject(String filePath) {
        LogUtil.info("Reading from " + filePath);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    builder.append(inputLine);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(builder.toString());
    }
}
