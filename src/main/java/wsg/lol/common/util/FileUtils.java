package wsg.lol.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsg.lol.common.pojo.base.BaseResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EastSunrise
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static BaseResult writeString2File(String str, String savePath) {
        logger.info("Writing to: " + savePath);
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
        logger.info("Reading from " + filePath);
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

    public static JSONArray readJSONArray(String filePath) {
        return JSON.parseArray(readString(filePath));
    }
}
