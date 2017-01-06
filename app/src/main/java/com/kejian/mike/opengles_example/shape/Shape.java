package com.kejian.mike.opengles_example.shape;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by kisstheraik on 17/1/6.
 */

public class Shape implements IBuffer{

    public float[] vlist ;
    public float[] colorArray;

    //获取点的坐标
    public float[] getVlist(){
        return vlist;
    }
    //获取边的个数
    public int getVNum(){
        return vlist==null?0:vlist.length/3;
    }

    @Override
    public Buffer getBuffer() {
        if(vlist==null){
            return null;
        }
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(vlist.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer=byteBuffer.asFloatBuffer();
        floatBuffer.put(vlist);
        floatBuffer.position(0);

        return  floatBuffer;

    }
    public void setColorArray(float[] colorArray){
        this.colorArray=colorArray;
    }
}
