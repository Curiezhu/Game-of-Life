package com.kejian.mike.opengles_example;


import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.kejian.mike.opengles_example.shape.Shape;
import com.kejian.mike.opengles_example.shape.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by kisstheraik on 17/1/5.
 *
 */

public class MyRender implements GLSurfaceView.Renderer {


    private Shape[][] shapeList;

    public MyRender(){

    }
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

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


    }
}
