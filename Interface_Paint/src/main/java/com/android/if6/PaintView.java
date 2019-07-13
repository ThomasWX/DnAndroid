package com.android.if6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PaintView extends View {

    private final Paint mPaint;


    public PaintView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 重置
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(200);
        // 设置画笔的样式
        mPaint.setStyle(Paint.Style.FILL); // 填充内容
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStyle(Paint.Style.STROKE); // 描边

        mPaint.setStrokeWidth(10); // 设置描边(画笔)宽度

        // 重点
        // 设置线 帽
        mPaint.setStrokeCap(Paint.Cap.BUTT); // 无
        mPaint.setStrokeCap(Paint.Cap.ROUND); // 圆形
        mPaint.setStrokeCap(Paint.Cap.SQUARE); // 方形
        // 线条交汇处
        mPaint.setStrokeJoin(Paint.Join.MITER); // 锐角
        mPaint.setStrokeJoin(Paint.Join.ROUND); // 圆弧
        mPaint.setStrokeJoin(Paint.Join.BEVEL); // 直线

        test1(canvas);
    }

    /**
     * 画线条，Path可以连线
     */
    private void test1(Canvas canvas) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(300, 200);
        path.lineTo(100, 300);
        path.lineTo(100, 100);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, mPaint);


//        path.moveTo(200,100);
//        path.lineTo(300,100);
//        path.lineTo(100,300);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        canvas.drawPaint(mPaint);
//
//
//        path.moveTo(300,100);
//        path.lineTo(300,100);
//        path.lineTo(100,300);
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);
//        canvas.drawPaint(mPaint);
    }

    /**
     * 文字绘制
     */
    private void test2(Canvas canvas) {
        mPaint.getFontSpacing(); // 获取字符行间距
        mPaint.getLetterSpacing(); // 获取字符之间的间距
        mPaint.setLetterSpacing(2f); // 设置字符间间距
        mPaint.setStrikeThruText(true); // 设置文本删除线
        mPaint.setUnderlineText(true); // 设置下划线
        mPaint.setTextSize(16); // 设置文字大小
        mPaint.setTypeface(Typeface.DEFAULT); // 设置字体类型
        // mPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));

        mPaint.setTextSkewX(-0.25f);// 设置文字倾斜，官方推荐 -0.25f 即斜体
        mPaint.setTextAlign(Paint.Align.LEFT); // 设置文本对齐方式，类似Word中的对齐


        String text = "字符hello123";
        mPaint.setTextSize(30);
        float[] measuredWidth = new float[1];
        // 计算指定字符串(字符长度、个数、显示时真实长度), 两个字母算一个字符，一个汉字算一个字符
        int num /*返回测量的字符数。 永远都是<= ABS（计数）*/ = mPaint.breakText(text,
                true/*是否从第一个字符开始从前往后测量，否则从最后一个字符开始从后往前测量*/,
                200/*指定测量累积的最大宽度*/
                , measuredWidth /*可选的, 如果不为null，则返回测量的实际宽度*/);

        Rect rect = new Rect();
        // 返回 Rect，获取文本的矩形区域(即可得到宽高)
        mPaint.getTextBounds(text, 0, 3, rect);

        // 获取文本的宽度，和上面getTextBounds类似，但是是一个比较粗略的结果
        mPaint.measureText(text);

        // 用于测量每一个字符的宽度,和上面measureText类似，但是是比较精准的结果
        float[] widths = new float[20];
        int textNum /*返回字符数*/ = mPaint.getTextWidths(text, widths/*用于接收字符前进宽度的数组，必须至少与文本一样大*/);
        // mPaint.getTextWidths(text, 0, 2, widths);


        // 基线
    }

    /**
     * 基线 自定义标签
     */
    private void test3(Canvas canvas) {
        String text = "Hello!";
        int top = 100;
        int baselineX = 0;
        mPaint.setTextSize(100);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setColor(Color.BLUE);

        Paint.FontMetrics metrics = mPaint.getFontMetrics();
//        metrics.top;
//        metrics.ascent;
//        metrics.descent;
//        metrics.bottom;
        // 这四个值都是以基线baseline为基准来计算，baseline以上是负的，baseline以下是正的
        float baselineY = top - metrics.top; // top + metrics.top的绝对值
        canvas.drawText(text, baselineX, baselineY, mPaint);
    }

    /**
     * 自定义进度条
     * @param canvas
     */
    private void test4(Canvas canvas){

    }
}
