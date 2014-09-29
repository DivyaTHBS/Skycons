package com.thbs.skycons.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class CloudView extends View {

    private Paint paint;
    private int screenW, screenH;
    private float X, Y;
    private Path path;
    private double count;
    double radius1, radius2;


    public CloudView(Context context, AttributeSet attrs) {
        super(context, attrs);

        String num1[] = attrs.getAttributeValue(0).split("\\.");
        String num2[] = attrs.getAttributeValue(1).split("\\.");

        screenW = Integer.valueOf(num1[0]);
        screenH = Integer.valueOf(num2[0]);

        X = screenW/2;
        Y = (screenH/2);

<<<<<<< HEAD
        radius1 = 90;
        radius2 = 50;
=======
//        path.moveTo(X, Y);
>>>>>>> 31ecf11b87fdd0f58ca31eb3b2754003debca09f

        init();
    }

<<<<<<< HEAD
=======
    public CloudView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //screenW = Integer.valueOf(attrs.getAttributeValue(0));
        //screenH = Integer.valueOf(attrs.getAttributeValue(1));

        X = screenW/2;
        Y = (screenH/2);

        //path.moveTo(X, Y);

        init();
    }


    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenW = w;
        screenH = h;

        X = screenW/2;
        Y = (screenH/2);

    }
>>>>>>> 31ecf11b87fdd0f58ca31eb3b2754003debca09f

    private void init() {

        count = 0;

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(0, 0, 0, Color.BLACK);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path = new Path();

        count = count+0.5;

        int retval = Double.compare(count, 360.00);

        if(retval == 0) {
            count = 0;
        }

        float X1 = (float)(radius1 * Math.cos(Math.toRadians(0+(0.222*count))) + X);
        float Y1 = ((float)(radius2 * Math.sin(Math.toRadians(0+(0.222*count))) + Y));
        float P1X = (float)(radius1 * Math.cos(Math.toRadians(80+(0.111*count))) + X);
        float P1Y = ((float)(radius2 * Math.sin(Math.toRadians(80+(0.111*count))) + Y));
        float P2X = (float)(radius1 * Math.cos(Math.toRadians(120+(0.222*count))) + X);
        float P2Y = ((float)((radius2+(0.111*count)) * Math.sin(Math.toRadians(120+(0.222*count))) + Y));
        float P3X = (float)(radius1 * Math.cos(Math.toRadians(200+(0.222*count))) + X);
        float P3Y = ((float)(radius1 * Math.sin(Math.toRadians(200+(0.222*count))) + Y));
        float P4X =(float)(radius1 * Math.cos(Math.toRadians(280+(0.222*count))) + X);
        float P4Y = ((float)(radius1 * Math.sin(Math.toRadians(280+(0.222*count))) + Y));

        path.moveTo(X1,Y1);

        PointF P1c1 = calculateTriangle(X1, Y1, P1X, P1Y, true, count);
        PointF P1c2 = calculateTriangle(X1, Y1, P1X, P1Y, false, count);
        PointF P2c1 = calculateTriangle(P1X, P1Y, P2X, P2Y, true, count);
        PointF P2c2 = calculateTriangle(P1X, P1Y, P2X, P2Y, false, count);
        PointF P3c1 = calculateTriangle(P2X, P2Y, P3X, P3Y, true, count);
        PointF P3c2 = calculateTriangle(P2X, P2Y, P3X, P3Y, false, count);
        PointF P4c1 = calculateTriangle(P3X, P3Y, P4X, P4Y, true, count);
        PointF P4c2 = calculateTriangle(P3X, P3Y, P4X, P4Y, false, count);
        PointF P5c1 = calculateTriangle(P4X, P4Y, X1, Y1, true, count);
        PointF P5c2 = calculateTriangle(P4X, P4Y, X1,Y1, false, count);

        path.moveTo(X1,Y1);
        path.cubicTo(P1c1.x,P1c1.y,P1c2.x,P1c2.y,P1X,P1Y);
        path.cubicTo(P2c1.x,P2c1.y,P2c2.x,P2c2.y,P2X,P2Y);
        path.cubicTo(P3c1.x,P3c1.y,P3c2.x,P3c2.y,P3X,P3Y);
        path.cubicTo(P4c1.x,P4c1.y,P4c2.x,P4c2.y,P4X,P4Y);
        path.cubicTo(P5c1.x,P5c1.y,P5c2.x,P5c2.y,X1,Y1);

        canvas.drawPath(path, paint);

        invalidate();

    }

    private PointF calculateTriangle(float x1, float y1, float x2,
                                     float y2, boolean left, double count) {

        PointF result = new PointF(0,0);
        float dy = y2 - y1;
        float dx = x2 - x1;
        float dangle = (float) ((Math.atan2(dy, dx) - Math.PI /2f));
        float sideDist = (float)0.5 * (float) Math.sqrt(dx * dx + dy * dy); //square

        if (left) {
            result.x = (int) (Math.cos(dangle) * sideDist + x1);
            result.y = (int) (Math.sin(dangle) * sideDist + y1);

        } else {
            result.x = (int) (Math.cos(dangle) * sideDist + x2);
            result.y = (int) (Math.sin(dangle) * sideDist + y2);
        }

        return result;

    }

}
