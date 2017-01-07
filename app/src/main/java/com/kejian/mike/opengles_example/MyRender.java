package com.kejian.mike.opengles_example;


import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.view.View;

import com.kejian.mike.opengles_example.font.FontManager;
import com.kejian.mike.opengles_example.gameOfLife.Controller;
import com.kejian.mike.opengles_example.shape.Shape;
import com.kejian.mike.opengles_example.shape.Square;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by kisstheraik on 17/1/5.
 *
 */

public class MyRender implements GLSurfaceView.Renderer {


    private Shape[][] shapeList;



    //修改方格状态
    public synchronized void setShapeList(Shape[][] list){
        this.shapeList=list;
    }
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f,0.0f,0.0f,0.5f);
        gl10.glShadeModel(GL10.GL_SMOOTH);
        gl10.glClearDepthf(1.0f);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

        gl10.glViewport(0,0,i,i1);
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        GLU.gluPerspective(gl10,45.0f,(float)i/(float)i1,0.1f,100.0f);
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

       draw(gl10);
        //tmpDraw(gl10);


    }
    //画一个图形树
    public void draw(GL10 gl){

        if(shapeList==null)return;

        //清屏成黑色
        gl.glClearColor(0f,0f,0f,0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        //初始化坐标系
        gl.glLoadIdentity();
        gl.glTranslatef(0,0,-3);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        Square square=new Square(2f,2.5f);
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, square.getBuffer());
        //第一个参数是图元，然后分别是开始索引和需要绘制的顶点的个数
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

        float startX=-0.4f;
        float startY=0.7f;

        for(int i=0;i<shapeList.length;i++){
            for(int j=0;j<shapeList[0].length;j++){

                Shape shape=shapeList[i][j];
                gl.glLoadIdentity();
                gl.glTranslatef(startX+(i*0.03f),startY-(j*0.03f),-2);
                gl.glColor4f(shape.colorArray[0],shape.colorArray[1],shape.colorArray[2],shape.colorArray[3]);
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, shape.getBuffer());
                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

            }
        }




        //文字

       // gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Replace the current matrix with the identity matrix
        gl.glLoadIdentity();
        // Translates 4 units into the screen.

        gl.glTranslatef(-0.28f, 0.4f, -1);

        Bitmap bitmap=new FontManager().getWord(0+"代",300,300);

        short[] indices = new short[] { 0, 1, 2, 1, 3, 2 };

        float[] vertices = new float[] { -0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f,
                -0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f };
//        Square square=new Square(1f,1f);
//        float[] vertices=square.getVlist();
        //整张图片的大小
        float textureCoordinates[] = { 0.0f, 1.0f, //
                1.0f, 1.0f, //
                0.0f, 0.0f, //
                1.0f, 0.0f, //
        };

        FloatBuffer mTextureBuffer=BufferUtil.getFloatBuffer(textureCoordinates);

        ShortBuffer shortBuffer=BufferUtil.getShortBuffer(indices);
        FloatBuffer floatBuffer=BufferUtil.getFloatBuffer(vertices);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);


        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        int mTextureId = textures[0];
        // ...and bind it to our array
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

        // Create Nearest Filtered Texture
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);

        // Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_REPEAT);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);



        gl.glEnable(GL10.GL_TEXTURE_2D);
        // Enable the texture state
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Point to our buffers
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);




        gl.glTranslatef(0.6f, -0.8f, -1);


        // Point out the where the color buffer is.
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, shortBuffer);
        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);


    }


}
