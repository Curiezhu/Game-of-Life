package com.kejian.mike.opengles_example.shape;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by kisstheraik on 17/1/6.
 * 长方形
 */

public class Square extends Shape{


    private float l;
    private float w;

    public Square(float l,float w){

        this.l=l;
        this.w=w;
        vlist=new float[12];

        float x= (float) (l/2.0);
        float y= (float) (w/2.0);

        vlist[0]=x;
        vlist[1]=y;
        vlist[2]=0f;

        vlist[3]=x;
        vlist[4]=-y;
        vlist[5]=0f;

        vlist[6]=-x;
        vlist[7]=-y;
        vlist[8]=0f;

        vlist[9]=-x;
        vlist[10]=y;
        vlist[11]=0f;


    }
    public Square(float l,float w,float[] colorArray){

        this(l,w);
        this.colorArray=colorArray;

    }
    public float getH(){

        return l;
    }
    public float getW(){

        return w;
    }
}
