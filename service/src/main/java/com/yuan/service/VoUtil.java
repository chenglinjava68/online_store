package com.yuan.service;

import com.yuan.common.utils.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author joryun ON 2017/10/22.
 */
public class VoUtil {
    public static <F, T> List<T> build(List<F> from, Class<T> toClass) {
        if (from == null || from.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        for (F f : from) {
            T t = BeanUtils.copy(f, toClass);
            result.add(t);
        }
        return result;
    }
}
