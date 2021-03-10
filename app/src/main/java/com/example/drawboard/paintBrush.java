package com.example.drawboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import static com.example.drawboard.MainActivity.paint;
import static com.example.drawboard.MainActivity.path;

public class paintBrush extends View {
    public static ArrayList<Path> pathArrayList = new ArrayList<>();
    public static ArrayList<Integer> colorList = new ArrayList<>();
    public static int currentBrush = Color.BLACK;
    public ViewGroup.LayoutParams params;
    public paintBrush(Context context) {
        super(context);
        init(context);
    }

    public paintBrush(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public paintBrush(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                invalidate();
                return true;
            case (MotionEvent.ACTION_MOVE):
                path.lineTo(x,y);
                pathArrayList.add(path);
                colorList.add(currentBrush);
                invalidate();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i<pathArrayList.size();i++){
            paint.setColor(colorList.get(i));
            canvas.drawPath(pathArrayList.get(i),paint);
            invalidate();
        }
    }
}
