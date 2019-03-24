package wsg.lol.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wsg.lol.pojo.base.BaseResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author King
 */
public class FileUtil {

    private static final String SYSTEM_SEPARATOR = "/";

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

    public static List<String[]> readCSV(String filePath, String commaDelimiter) {
        List<String[]> records = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(commaDelimiter);
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static String readString(String filePath) {
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
        return builder.toString();
    }

    public static JSONObject readJSONObject(String filePath) {
        return JSON.parseObject(readString(filePath));
    }
}
