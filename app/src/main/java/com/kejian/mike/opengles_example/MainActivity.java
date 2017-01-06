package com.kejian.mike.opengles_example;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kejian.mike.opengles_example.gameOfLife.Controller;
import com.kejian.mike.opengles_example.shape.Square;
import com.kejian.mike.opengles_example.shape.Star;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        LinearLayout linearLayout = new LinearLayout(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        linearLayout.addView(glSurfaceView,layoutParams);

        setContentView(linearLayout);

        final Controller controller=new Controller();
        controller.setCheckerboard();

        glSurfaceView.setRenderer(controller.myRender);

        new Thread(new Runnable() {
            @Override
            public void run() {
                controller.startLoop();
            }
        }).start();

    }


}
