package wsg.lol.common.utils;

import java.io.*;
import java.net.URL;

/**
 * @author King
 */
public class HttpHelper {

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
