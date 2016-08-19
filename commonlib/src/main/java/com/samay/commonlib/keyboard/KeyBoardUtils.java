package com.samay.commonlib.keyboard;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by shaohua.li on 8/19/16.
 */
public class KeyBoardUtils {
    private static volatile KeyBoardUtils instance = null;

    public static KeyBoardUtils getInstance() {
        if (instance == null) {
            synchronized (KeyBoardUtils.class) {
                if (instance == null) {
                    instance = new KeyBoardUtils();
                }
            }
        }
        return instance;
    }

    private void showKeyboard(Context mContext,View mView){
        InputMethodManager imm= (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(mView.getWindowToken(),0);
    }

    private void hideKeyboard(Context mContext,View mView){
        InputMethodManager imm= (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mView.getWindowToken(),0);
    }
}
