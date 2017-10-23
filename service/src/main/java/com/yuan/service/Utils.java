package com.yuan.service;

/**
 * @author joryun ON 2017/10/22.
 */
public class Utils {
    public static long sum(Long... values) {
        long result = 0L;
        for (Long value : values) {
            if (value != null) {
                result += value;
            }
        }
        return result;
    }
}
