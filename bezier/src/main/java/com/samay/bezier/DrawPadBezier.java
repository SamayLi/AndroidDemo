package com.samay.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by shaohua.li on 7/29/16.
 */
public class DrawPadBezier extends View {
    private float mX;
    private float mY;
    private float offset= ViewConfiguration.get(getContext()).getScaledTouchSlop();
    private Paint mPaint;
    private Path mPath;

    public DrawPadBezier(Context context) {
        this(context, null);
    }

    public DrawPadBezier(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPadBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPath=new Path();
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                float x=event.getX();
                float y=event.getY();
                mX=x;
                mY=y;
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                float x1=event.getX();
                float y1=event.getY();
                float preX=mX;
                float preY=mY;
                float dx=Math.abs(x1-preX);
                float dy=Math.abs(y1-preY);
                if(dx>=offset|| dy>=offset){
                    float cx=(x1+preX)/2;
                    float cy=(y1+preY)/2;
                    mPath.quadTo(preX,preY,cx,cy);
                    mX=x1;
                    mY=y1;
                }
                break;
        }
        invalidate();
        return true;
    }

}
