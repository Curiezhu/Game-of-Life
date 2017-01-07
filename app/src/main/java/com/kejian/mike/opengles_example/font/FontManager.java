package com.kejian.mike.opengles_example.font;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by kisstheraik on 17/1/6.
 * 使用纹理贴图的方式来绘制文字
 */

public class FontManager {

    public Bitmap getWord(String text, int h, int w){

        Bitmap bitmap=Bitmap.createBitmap( h ,w , Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();

        paint.setStrokeWidth(3);
        paint.setTextSize(100);
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.BLACK);

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (h - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(text,w / 2 - bounds.width() / 2, baseline, paint);


        return bitmap;
    }

}
