package com.andpack.annotation.framework;

import android.text.TextUtils;

import com.andframe.annotation.Must;
import com.andframe.exception.AfToastException;
import com.andframe.util.java.AfReflecter;

import java.lang.reflect.Field;

/**
 * Must 检测器
 * Created by Administrator on 2016/3/2.
 */
public class MustChecker {

    public static void checkMust(Object obj) throws Exception {
        for (Field field: AfReflecter.getFieldAnnotation(obj.getClass(), Must.class)) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value == null || (value instanceof String && TextUtils.isEmpty(value.toString()))) {
                throw new AfToastException(field.getAnnotation(Must.class).value());
            }
        }
    }

}
