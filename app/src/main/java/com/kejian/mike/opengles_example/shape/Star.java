package com.kejian.mike.opengles_example.shape;

import android.util.Log;

/**
 * Created by kisstheraik on 17/1/6.
 * 五角星，根据外切圆半径确定大小，当前坐标的原点作为中心
 */

public class Star extends Shape{



    public Star(float r){
        setVlist(r);
    }

    //根据外接圆半径来设置坐标
    private void setVlist(float r){

        vlist=new float[15];

        vlist[0]=0f;
        vlist[1]=r;
        vlist[2]=0f;

        vlist[3]=(float) Math.cos(Math.toRadians(18))*r;
        vlist[4]=(float)Math.sin(Math.toRadians(18))*r;
        vlist[5]=0f;

        vlist[6]=(float) Math.cos(Math.toRadians(54))*r;
        vlist[7]=-(float)Math.sin(Math.toRadians(54))*r;
        vlist[8]=0f;

        vlist[9]=-(float) Math.cos(Math.toRadians(54))*r;
        vlist[10]=-(float)Math.sin(Math.toRadians(54))*r;
        vlist[11]=0f;

        vlist[12]=-(float) Math.cos(Math.toRadians(18))*r;
        vlist[13]=(float)Math.sin(Math.toRadians(18))*r;
        vlist[14]=0f;



    }

}
