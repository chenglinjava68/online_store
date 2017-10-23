package com.yuan.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author joryun ON 2017/10/22.
 * <p>
 * 客户端导出工具类
 */
public class ExportUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExportUtil.class);

    /**
     * 导出文件到客户端
     *
     * @param response
     * @param contentType
     * @param path
     * @param fileName
     */
    public static void exportToClient(HttpServletResponse response, String contentType, String path, String fileName) {
        InputStream in = null;
        OutputStream os = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(contentType + ";charset=UTF-8");
            //response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes
            // ("UTF-8"), "ISO-8859-1"));
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            in = new FileInputStream(path + fileName);
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = in.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
        } catch (Exception e) {
            logger.info("导出文件异常", e);
        } finally {
            try {
                os.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            File file = new File(path + fileName);
//            file.delete();
        }
    }

}
