package com.samay.customviewdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import org.w3c.dom.Element;

/**
 * Created by shaohua.li on 5/14/16.
 */
public class Blur {
    private static final String TAG="Blur";

    public static Bitmap fastblur(Context context,Bitmap inBitmap,int radius){
        Bitmap bitmap=inBitmap.copy(inBitmap.getConfig(),true);
        final RenderScript rs=RenderScript.create(context);
        final Allocation input=Allocation.createFromBitmap(rs, inBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        final Allocation output=Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script=ScriptIntrinsicBlur.create(rs, android.renderscript.Element.U8_4(rs));
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);
        return bitmap;
    }
}
