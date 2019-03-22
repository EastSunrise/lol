package wsg.lol.common.utils;

import wsg.lol.pojo.exception.MyHTTPException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author King
 */
public class HttpHelper {

    public static final String HTTPS = "https://";

    public static String getJSONString(String url, Map<String, String> requestHeaders) {
        LogUtil.info("Getting from " + url);
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getDataFromConnection(urlConnection, requestHeaders);
    }

    public static String postJSONString(String url, Map<String, Object> bodyParams,
                                        Map<String, String> requestHeaders) {
        LogUtil.info("Posting from " + url);
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(CodeUtil.encodeMap2UrlParams(bodyParams));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getDataFromConnection(urlConnection, requestHeaders);
    }

    private static String getDataFromConnection(HttpURLConnection urlConnection, Map<String, String> requestHeaders) throws MyHTTPException {
        for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
            urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        StringBuilder builder = new StringBuilder();
        try {
            int responseCode = urlConnection.getResponseCode();
            if (responseCode != 200) {
                if (400 == responseCode) {
                    LogUtil.info("Bad Request.");
                } else if (401 == responseCode) {
                    LogUtil.info("Unauthoritzed.");
                } else if (403 == responseCode) {
                    LogUtil.info("Forbidden");
                } else if (404 == responseCode) {
                    LogUtil.info("Not Found");
                } else if (415 == responseCode) {
                    LogUtil.info("Unsupported Media Type");
                } else if (429 == responseCode) {
                    LogUtil.info("Rate Limit Exceeded");
                    Date retry = DateUtil.getDate(urlConnection.getHeaderField("Retry-After"),
                            DateUtil.RETRY_FORMAT, "GMT", Locale.ENGLISH);
                    urlConnection.disconnect();
                    DateUtil.threadSleep(retry.getTime() - System.currentTimeMillis());
                    urlConnection.connect();
                    // wsg
                }
                return "";
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
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
