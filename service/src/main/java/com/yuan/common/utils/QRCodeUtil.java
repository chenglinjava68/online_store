package com.yuan.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author joryun ON 2017/10/22.
 * <p>
 * 二维码生成及导出工具类
 */
public class QRCodeUtil {

    private static Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);


    /***
     * 生成二维码
     * @param qrCodePath
     * @param content
     * @param filename
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void createQRCode(String qrCodePath, String content, String filename) {
        Map<EncodeHintType, Object> hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashMap.put(EncodeHintType.MARGIN, 1);
        try {
            File qrCodeFile = new File(qrCodePath);
            if (!qrCodeFile.exists()) {
                // 创建二维码生成目录
                qrCodeFile.mkdirs();
            }
            logger.info(" File name is : " + filename);
            File file = new File(qrCodePath + filename + ".png");
            String fileEncode = System.getProperty("file.encoding");
            logger.info(" System file encoding is : " + fileEncode);
            logger.info(" file is : " + file);
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                        BarcodeFormat.QR_CODE, 300, 300, hashMap);
                MatrixToImageWriter.writeToPath(bitMatrix, "png", file.toPath());
            }
        } catch (Exception e) {
            logger.info(" createQRCode error ", e);
        }
    }

    /**
     * 将文件打包成zip
     *
     * @param qrCodePath
     * @param zipFilePath
     * @param fileName
     */
    public static void fileToZip(String qrCodePath, String zipFilePath, String fileName) {
        File sourceFile = new File(qrCodePath);
        FileInputStream fis;
        BufferedInputStream bis = null;
        FileOutputStream fos;
        ZipOutputStream zos = null;
        try {
            File zipFile = new File(zipFilePath + File.separator + fileName);
            if (zipFile.exists()) {
                logger.info(" 当前目录已存在该zip文件 ");
            } else {
                File[] sourceFiles = sourceFile.listFiles();
                if (null == sourceFiles || sourceFiles.length < 1) {
                    logger.info(" 当前目录不存在待压缩文件 ");
                } else {
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));
                    byte[] bufs = new byte[1024 * 2];
                    for (int i = 0; i < sourceFiles.length; i++) {
                        //创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024 * 2);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 2)) != -1) {
                            zos.write(bufs, 0, read);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bis) bis.close();
                if (null != zos) zos.close();
            } catch (IOException e) {
                logger.info(" fileToZip close error ");
            }
        }
    }

}
