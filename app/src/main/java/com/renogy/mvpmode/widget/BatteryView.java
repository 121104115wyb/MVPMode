package com.renogy.mvpmode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author Create by 17474 on 2021/6/30.
 * Email： lishuwentimor1994@163.com
 * Describe：电池
 */
public class BatteryView extends View {

    //当前电量
    private Paint batteryPaint;
    //闪电图标
    private Paint lightingPaint;
    //当前电量，计算坐标
    private double soc;
    private LinearGradient batteryGradient;
    private int startColor;
    private int endColor;

    public BatteryView(Context context) {
        this(context, null);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultHeight = 200;
        int width = getSize(defaultHeight / 2, widthMeasureSpec);
        int height = getSize(defaultHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);

        Log.d("onMeasure", "width:" + width + "height:" + height);
    }

    //重新测量宽高
    private int getSize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        //相当于我们设置为wrap_content，//相当于我们设置为match_parent或者为一个具体的值
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY) {
            mySize = size;
        }
        return mySize;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        int batteryWidth = height / 2;
        int startX = (width - batteryWidth) / 2;
        int header = batteryWidth / 3;
        Log.d("BatteryView", "drawRect:--height: " + height + "--width--" + width + "--batteryWidth--" + batteryWidth + "--startX:" + startX);
        //画电池的头部
        int batteryHeaderHeight = 20;
        canvas.drawRect(startX + header, 0, startX + header * 2, batteryHeaderHeight, lightingPaint);
        //画电池的总量
        canvas.drawRect(startX, batteryHeaderHeight, startX + batteryWidth, height, lightingPaint);
        batteryGradient = new LinearGradient(startX, 0, startX, height, startColor, endColor, Shader.TileMode.CLAMP);
        batteryPaint.setShader(batteryGradient);
        canvas.drawRect(startX, (int) (height * (1 - soc) + 20), startX + batteryWidth, height, batteryPaint);

//        int lightStartX = width / 2;
//        int lightStartY = (height - batteryHeaderHeight) / 2;
//        canvas.drawLine(lightStartX, lightStartY, lightStartX - 6, lightStartY + 8, lightingPaint);
//        canvas.drawLine(lightStartX - 6, lightStartY + 8, lightStartX + 6, lightStartY + 8, lightingPaint);
//        canvas.drawLine(lightStartX + 6, lightStartY + 8, lightStartX - 12, lightStartY + 22, lightingPaint);
//        canvas.drawLine(lightStartX - 12, lightStartY + 22, lightStartX - 6, lightStartY + 14, lightingPaint);
//        canvas.drawLine(lightStartX - 6, lightStartY + 14, lightStartX - 12, lightStartY + 14, lightingPaint);
//        canvas.drawLine(lightStartX - 12, lightStartY + 14, lightStartX, lightStartY, lightingPaint);
    }


    private void init() {
        batteryPaint = new Paint();
        batteryPaint.setStrokeWidth(8);
        batteryPaint.setAntiAlias(true);
        batteryPaint.setStrokeJoin(Paint.Join.ROUND);
        batteryPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        lightingPaint = new Paint();
        lightingPaint.setStrokeWidth(10);
        lightingPaint.setAntiAlias(true);
        batteryPaint.setStrokeJoin(Paint.Join.ROUND);
        batteryPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        lightingPaint.setColor(Color.parseColor("#4D000000"));
        setSoc(0.6);
    }


    /**
     * 设置soc
     *
     * @param soc 当前电量
     */
    public void setSoc(double soc) {
        this.soc = soc;
        updateColor(soc);
        invalidate();
    }

    //更新渐变色
    private void updateColor(double soc) {
        int socInt = (int) (100 * soc);
        if (socInt < 20) {
            startColor = Color.parseColor("#FF5252");
            endColor = Color.parseColor("#FF9EB9");
        } else if (socInt < 50) {
            startColor = Color.parseColor("#FFB048");
            endColor = Color.parseColor("#FFE922");
        } else {
            startColor = Color.parseColor("#91FF60");
            endColor = Color.parseColor("#5DE6FF");
        }
    }

}
