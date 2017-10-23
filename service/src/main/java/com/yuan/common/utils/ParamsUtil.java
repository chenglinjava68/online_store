package com.yuan.common.utils;

import java.io.File;

public class ParamsUtil {

    public static final String WEBAPP_EXPORT_EXCEL = System.getProperty("java.io.tmpdir") + File.separator + "kongfu" + File.separator
            + "excel" + File.separator;

    public static final String WEBAPP_EXPORT_STUDENT_QRCODE = System.getProperty("java.io.tmpdir") + File.separator + "kongfu" + File.separator
            + "student_qrcode" + File.separator;

    public static final String WEBAPP_EXPORT_SHOP_QRCODE = System.getProperty("java.io.tmpdir") + File.separator + "kongfu" + File.separator
            + "shop_qrcode" + File.separator;

    public static final String WEBAPP_EXPORT_CLIENTSHOP_QRCODE = System.getProperty("java.io.tmpdir") + File.separator + "kongfu" + File.separator
            + "client_shop_qrcode" + File.separator;
}
