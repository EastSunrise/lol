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
    private static final String USER_AGENT = "Mozilla/5.0";

    private static int accessCount = 1;

    // wsg response code
    public static String getJSONString(String url) {
        System.out.println("Getting from " + url);
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestProperty("User-Agent", USER_AGENT);
            return getDataFromConnection(urlConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String postJSONString(String url, Map<String, Object> params) {
        System.out.println("Posting from " + url);
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("POST");
            setPostOrPutHeader(params, urlConnection);
            return getDataFromConnection(urlConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String putJSONString(String url, Map<String, Object> params) {
        System.out.println("Putting from " + url);
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("PUT");
            setPostOrPutHeader(params, urlConnection);
            return getDataFromConnection(urlConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void setPostOrPutHeader(Map<String, Object> params, HttpURLConnection urlConnection) throws IOException {
        urlConnection.setRequestProperty("User-Agent", USER_AGENT);
        urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        urlConnection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
        outputStream.writeBytes(CodeUtil.encodeMap2UrlParams(params));
        outputStream.flush();
        outputStream.close();
    }

    // wsg rate limit
    // wsg 连接超时
    private static String getDataFromConnection(HttpURLConnection urlConnection) {
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
            System.out.print(accessCount++ + "   ");
            if (accessCount % 20 == 0)
                Thread.sleep(1000);
            if (accessCount % 100 == 0)
                Thread.sleep(120000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void downloadData(String urlStr, String savePath) {
        System.out.println("Downloading from: " + urlStr);
        System.out.println("To: " + savePath);
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
