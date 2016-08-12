package com.samay.commonlib.display;

import android.content.Context;

/**
 * Created by shaohua.li on 8/10/16.
 */
public class DensityUtil {
    private static volatile DensityUtil instance = null;

    public static DensityUtil getInstance() {
        if (instance == null) {
            synchronized (DensityUtil.class) {
                if (instance == null) {
                    instance = new DensityUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
