package com.android.if6;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ProgressBarView extends View {
    private int style;
    private float textSize;
    private float roundWidth;
    private boolean textShow;
    private int textColor;
    private int roundProgressColor;
    private int roundColor;
    private int max;

    private int progress;
    private Paint mPaint;

    public static final int STROKE = 0;
    public static final int FILL = 1;

    public ProgressBarView(Context context) {
        super(context);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressBarView);
        max = typedArray.getInteger(R.styleable.ProgressBarView_max, 100);
        roundColor = typedArray.getColor(R.styleable.ProgressBarView_roundColor, Color.GREEN);
        roundProgressColor = typedArray.getColor(R.styleable.ProgressBarView_roundProgressColor, Color.GREEN);
        textColor = typedArray.getColor(R.styleable.ProgressBarView_textColor, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.ProgressBarView_textSize, 50);
        roundWidth = typedArray.getDimension(R.styleable.ProgressBarView_roundWidth, 10);
        textShow = typedArray.getBoolean(R.styleable.ProgressBarView_textShow, true);
        style = typedArray.getInt(R.styleable.ProgressBarView_style, 0);


        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画默认大圆环
        int centerX = getWidth() / 2; // 中心坐标点
        int centerY = centerX;
        float radius = centerX - roundWidth / 2; // 半径  roundWidth /2 --> 圆环宽度
        mPaint.setColor(roundColor);
        mPaint.setStyle(Paint.Style.STROKE); // 设置样式为空心(描边)
        mPaint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿

        canvas.drawCircle(centerX, centerY, radius, mPaint);// 中心点的x、y 坐标，半径，paint


        // 画进度百分比
        mPaint.reset();
        mPaint.setColor(textColor);
        mPaint.setStrokeWidth(0); // 圆环的宽度
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT.BOLD);

        int percent = (int) (progress / (float) max * 100); // int 除 int 容易出问题，故先加float ，结果再强转int.
        if (textShow && percent !=0 && style == STROKE){
            canvas.drawText(percent + "%",(getWidth() - mPaint.measureText(percent+"%"))/2f,
                    // y 公式：float baselineY = centerY + (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom
                    getWidth()/2f - (mPaint.descent()+mPaint.ascent())/2f,mPaint
                    );
        }
        // 画圆弧
        // 矩形区域，定义圆弧的形状大小
        float rectLeft = centerX - radius;
        float  rectTop = rectLeft;
        float  rectRight = centerX + radius;
        float  rectBottom = rectRight;
        RectF oval = new RectF(rectLeft, rectTop, rectRight, rectBottom);

        mPaint.setColor(roundProgressColor);
        mPaint.setStrokeWidth(roundWidth);
        switch (style){
            case STROKE:
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval,0,360*progress/max,false,mPaint);
                break;
            case FILL:
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0){
                    canvas.drawArc(oval,0,360 * progress / max,true,mPaint);
                }
                break;

        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if (max < 0) {
            return;
        }
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            return;
        }
        if (progress <= max) {
            this.progress = progress;
            // 在主线程或子线程调用，用此方法更新界面
            postInvalidate();
        }
    }
}
