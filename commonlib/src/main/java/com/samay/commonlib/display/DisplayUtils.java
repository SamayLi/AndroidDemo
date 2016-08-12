package com.samay.commonlib.display;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by shaohua.li on 8/10/16.
 */
public class DisplayUtils {

    private static volatile DisplayUtils instance = null;

    public static DisplayUtils getInstance() {
        if (instance == null) {
            synchronized (DisplayUtils.class) {
                if (instance == null) {
                    instance = new DisplayUtils();
                }
            }
        }

        return instance;
    }

    public String getResolution(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(metric);

        int width = metric.widthPixels; // 宽度（PX）
        int height = metric.heightPixels; // 高度（PX）

        return width + "*" + height;
    }

    public int getDPI(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(metric);
        int width = metric.widthPixels;
        int height = metric.heightPixels;
        double x = Math.pow(width / metric.xdpi, 2);
        double y = Math.pow(height / metric.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        int dpi = (int) (Math.sqrt(width * width + height * height) / screenInches);
        return dpi;
    }

    public String getScreenSize(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(metric);
        int width = metric.widthPixels;
        int height = metric.heightPixels;
        double x = Math.pow(width / metric.xdpi, 2);
        double y = Math.pow(height / metric.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        //Defect 2114236 modify by shaohuali@tcl.com begin
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
        df.applyPattern("#.00");
        //Defect 2114236 modify by shaohuali@tcl.com end
        return df.format(screenInches);
    }

    public static final int PORTRAIT = 1;
    public static final int LANDSCAPE = 2;

    public void setScreenOrientation(Activity mActivity, int mode) {
        if (mode == PORTRAIT) {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
