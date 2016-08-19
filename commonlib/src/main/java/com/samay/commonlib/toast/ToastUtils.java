package com.samay.commonlib.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shaohua.li on 8/12/16.
 */
public class ToastUtils {
    private static Toast toast;

    public void showToast(Context context,String content){
        if(toast==null){
            toast=Toast.makeText(context,content,Toast.LENGTH_LONG);
        }else {
            toast.setText(content);
        }
        toast.show();
    }
}
