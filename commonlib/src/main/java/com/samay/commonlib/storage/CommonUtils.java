package com.samay.commonlib.storage;

import android.content.Context;
import android.content.res.Resources;

import com.samay.commonlib.R;

import java.util.Locale;

/**
 * Created by shaohua.li on 8/10/16.
 */
public class CommonUtils {
    public static String formatFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        float f = (float) size / mb;
        //Defect 2114236 modify by shaohuali@tcl.com begin
        return String.format(Locale.ENGLISH, "%.2f MB", f); // MODIFIED by junping.zhang, 2016-05-25,BUG-2167946
        //Defect 2114236 modify by shaohuali@tcl.com end
    }

    public static String convertStorage(long size, Context context) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        Resources res = context.getResources();

        if (size >= gb) {
            return String.format(Locale.US, "%.1f " + res.getString(R.string.gb), (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(Locale.US, f > 100 ? "%.0f " + res.getString(R.string.mb) : "%.1f " + res.getString(R.string.mb), f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(Locale.US, f > 100 ? "%.0f " + res.getString(R.string.kb) : "%.1f " + res.getString(R.string.kb), f);
        } else
            return String.format(Locale.US, "%d " + res.getString(R.string.b), size);
    }

    public static String getSufix(String src, Context context) {
        Resources res = context.getResources();
        if (src.endsWith(res.getString(R.string.gb))) {
            return res.getString(R.string.gb);
        } else if (src.endsWith(res.getString(R.string.mb))) {
            return res.getString(R.string.mb);
        } else if (src.endsWith(res.getString(R.string.kb))) {
            return res.getString(R.string.kb);
        }
        return res.getString(R.string.b);
    }
}
