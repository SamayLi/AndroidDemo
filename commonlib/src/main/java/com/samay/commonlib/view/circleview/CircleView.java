package com.samay.commonlib.view.circleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.samay.commonlib.R;

/**
 * Created by shaohua.li on 8/19/16.
 */
public class CircleView extends View {
    private int type;
    private static final int TYPE_CIRCLE=0;
    private static final int TYPE_ROUND=1;

    private Bitmap mSrc;
    private int mRadius;
    private int mWidth;
    private int mHeight;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.CircleView_borderRadius) {
                mRadius = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                        getResources().getDisplayMetrics()));

            } else if(attr==R.styleable.CircleView_src){
                mSrc= BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                break;
            }else if(attr==R.styleable.CircleView_type){
                type=typedArray.getInt(attr,0);
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specMode=MeasureSpec.getMode(widthMeasureSpec);
        int specSize=MeasureSpec.getSize(widthMeasureSpec);
        if(specMode==MeasureSpec.EXACTLY){
            mWidth=specSize;
        }else {
            int desireByImg=getPaddingLeft()+getPaddingRight()+mSrc.getWidth();
            if(specMode==MeasureSpec.AT_MOST){
                mWidth=Math.min(desireByImg,specSize);
            }
        }

        specMode=MeasureSpec.getMode(heightMeasureSpec);
        specSize=MeasureSpec.getSize(heightMeasureSpec);
        if(specMode==MeasureSpec.EXACTLY){
            mHeight=specSize;
        }else {
            int desire=getPaddingTop()+getPaddingBottom()+mSrc.getHeight();
            if(specMode==MeasureSpec.AT_MOST){
                mWidth=Math.min(desire,specSize);
            }
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    private Bitmap createCircleImage(Bitmap source,int min){
        final Paint paint=new Paint();
        paint.setAntiAlias(true);
        Bitmap target=Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(target);

        canvas.drawCircle(min / 2, min / 2, min / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    private Bitmap creatRoundImage(Bitmap source){
        final Paint paint=new Paint();
        paint.setAntiAlias(true);
        Bitmap target=Bitmap.createBitmap(mWidth,mHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(target);
        RectF rectF=new RectF(0,0,source.getWidth(),source.getHeight());
        canvas.drawRoundRect(rectF, 50f, 50f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source,0,0,paint);
        return target;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (type){
            case TYPE_CIRCLE:
                int min=Math.min(mHeight,mWidth);
                mSrc=Bitmap.createScaledBitmap(mSrc,min,min,false);
                canvas.drawBitmap(createCircleImage(mSrc,min),0,0,null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(creatRoundImage(mSrc),0,0,null);
                break;
        }
    }
}
