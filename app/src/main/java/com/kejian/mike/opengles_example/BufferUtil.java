package com.kejian.mike.opengles_example;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * Created by kisstheraik on 17/1/6.
 */

public class BufferUtil {
    public static ByteBuffer getByteBuffer(){
        return null;
    }
    public static ShortBuffer getShortBuffer(short[] shorts){
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(shorts.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        ShortBuffer intBuffer=byteBuffer.asShortBuffer();
        intBuffer.put(shorts);
        intBuffer.position(0);
        return intBuffer;
    }
    public static IntBuffer getIntBuffer(int[] l){
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(l.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer=byteBuffer.asIntBuffer();
        intBuffer.put(l);
        intBuffer.position(0);
        return intBuffer;
    }
    public static FloatBuffer getFloatBuffer(float[] floats){
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(floats.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer=byteBuffer.asFloatBuffer();
        floatBuffer.put(floats);
        floatBuffer.position(0);
        return floatBuffer;
    }
}
