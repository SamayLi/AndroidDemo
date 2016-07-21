package com.samay.customviewdemo.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by shaohua.li on 7/21/16.
 */
public class MyPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_ALPHA = 0.5f;
    private static final float MIN_ROTATION_X = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        float scaleFactor = Math.max(0.9f, 1 - Math.abs(position));
        float vertMargin = pageHeight * (1 - scaleFactor) / 2;
        float horzMargin = pageWidth * (1 - scaleFactor) / 2;
        if (position < -1) {
            view.setAlpha(0f);
        } else if (position < 0) {
            view.setTranslationX(horzMargin - vertMargin / 2);
            view.setScaleX(0.8f);
            view.setScaleY(0.8f);
            view.setRotationY(30f * position);
        } else if (position == 0) {
            view.setScaleX(1f);
            view.setScaleY(1f);
            view.setAlpha(1f);
        } else if (position <= 1) {
            view.setTranslationX(-horzMargin + vertMargin / 2);
            view.setScaleX(0.8f);
            view.setScaleY(0.8f);
            view.setRotationY(30f * position);
        }
        view.setAlpha(Math.max(0.6f,1-Math.abs(position)));
    }
}
