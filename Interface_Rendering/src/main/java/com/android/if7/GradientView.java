package com.android.if7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GradientView extends View {
    private BitmapShader bitmapShader;
    private Bitmap bitmap;
    private Paint paint;
    private Rect rect;
    public GradientView(Context context) {
        super(context);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_wx);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        rect = new Rect(0,0,1000,1000);
    }

    public GradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_wx);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        rect = new Rect(0,0,1000,1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(bitmapShader);
        canvas.drawRect(rect, paint);

    }
}
