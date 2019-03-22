package wsg.lol.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author King
 * @date 2019/2/13
 */
public class HttpHelper {

    public static final String HTTPS = "https://";

    public static String getJSONString(String url, Map<String, String> requestHeaders) {
        LogUtil.info("Getting from " + url);
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            return getDataFromConnection(urlConnection, requestHeaders);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String postJSONString(String url, Map<String, Object> bodyParams,
                                        Map<String, String> requestHeaders) {
        LogUtil.info("Posting from " + url);
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(CodeUtil.encodeMap2UrlParams(bodyParams));
            outputStream.flush();
            outputStream.close();
            return getDataFromConnection(urlConnection, requestHeaders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getDataFromConnection(HttpURLConnection urlConnection, Map<String, String> requestHeaders) {
        for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
            urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        StringBuilder builder = new StringBuilder();
        try {
            if (urlConnection.getResponseCode() != 200) {
                throw new Exception(urlConnection.getResponseMessage());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void downloadData(String urlStr, String savePath) {
        LogUtil.info("Downloading from: " + urlStr + "  to: " + savePath);
        File saveFile = new File(savePath);
        File dir = saveFile.getParentFile();
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                try {
                    throw new Exception("创建目录失败：" + dir.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        URL url;
        try {
            url = new URL(urlStr);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());

            dataInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
