package com.samay.commonlib.Log;

import android.util.Log;

/**
 * Created by shaohua.li on 8/12/16.
 */
public class AppLog {

    private static final String TAG = "";
    /**
     * Flag to turn on or off debug logs.
     */
    private static final boolean DEBUG = true;

    /**
     * Flag to turn on or off verbose logs.
     */
    private static final boolean VERBOSE = true;

    /**
     * Flag to turn on or off info logs.
     */
    private static final boolean INFO = true;

    /**
     * Flag to turn on or off warn logs.
     */
    private static final boolean WARN = true;

    /**
     * Flag to turn on or off error logs.
     */
    private static final boolean ERROR = true;

    /**
     * Simple method to write debug log.This method will do nothing if DEBUG is
     * false.
     */
    public static void d(String tag, String log) {
        if (DEBUG) {
            Log.d(TAG + "@@@" + tag, log);
            //no need to record
//			mInstance.recordLog(formatAndEncryptLog(tag,log));
        }
    }

    /**
     * Simple method to write debug log.This method will do nothing if DEBUG is
     * false. This method use OnetouchOptimizer@@@ as tag.
     */
    public static void d(String log) {
        d("", log);
    }

    /**
     * Simple method to write debug log.This method will do nothing if VERBOSE is
     * false.
     */
    public static void v(String tag, String log) {
        if (VERBOSE) {
            Log.v(TAG + "@@@" + tag, log);
        }
    }


    /**
     * Simple method to write verbose log.This method will do nothing if VERBOSE is
     * false. This method use OnetouchOptimizer@@@ as tag.
     */
    public static void v(String log) {
        v("", log);
    }

    /**
     * Simple method to write debug log.This method will do nothing if INFO is
     * false.
     */
    public static void i(String tag, String log) {
        if (INFO) {
            Log.i(TAG + "@@@" + tag, log);

        }
    }


    /**
     * Simple method to write verbose log.This method will do nothing if INFO is
     * false.
     */
    public static void i(String log) {
        i("", log);
    }


    /**
     * Simple method to write debug log.This method will do nothing if WARN is
     * false.
     */
    public static void w(String tag, String log) {
        if (WARN) {
            Log.w(TAG + "@@@" + tag, log);

        }
    }

    /**
     * For special case
     */
    public static void w(String log) {
        w("", log);
    }


    /**
     * Simple method to write debug log.This method will do nothing if ERROR is
     * false.
     */
    public static void e(String tag, String log) {
        if (ERROR) {
            Log.e(TAG + "@@@" + tag, log);

        }
    }

    /**
     * For special case
     */
    public static void e(String log) {
        e("", log);
    }
}

