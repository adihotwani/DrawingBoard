package com.example.drawboard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import static com.example.drawboard.paintBrush.colorList;
import static com.example.drawboard.paintBrush.currentBrush;
import static com.example.drawboard.paintBrush.pathArrayList;

public class MainActivity extends AppCompatActivity {
    public static Path path = new Path();
    public static Paint paint = new Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton pencil = findViewById(R.id.pencil);
        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(Color.BLACK);
                currentColor(paint.getColor());
            }
        });
        ImageButton eraser = findViewById(R.id.eraser);
        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pathArrayList.clear();
                colorList.clear();
                path.reset();
            }
        });
    }
    public void currentColor(int c){
        currentBrush = c;
        path = new Path();
    }
}