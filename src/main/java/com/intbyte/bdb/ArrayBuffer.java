package com.intbyte.bdb;

import com.intbyte.bdb.node.*;
import com.intbyte.bdb.node.array.ArrayNode;
import com.intbyte.bdb.node.array.ByteArrayNode;
import com.intbyte.bdb.node.array.DoubleArrayNode;
import com.intbyte.bdb.node.array.FloatArrayNode;
import com.intbyte.bdb.node.array.IntArrayNode;
import com.intbyte.bdb.node.array.LongArrayNode;
import com.intbyte.bdb.node.array.ShortArrayNode;

import java.nio.*;
import java.util.ArrayList;

public class ArrayBuffer {
    private int size = 0;

    ArrayList<ArrayNode<?>> nodes = new ArrayList<>();

    public void put(byte[] array) {
        nodes.add(new ByteArrayNode(array, size));
        size += 8 + array.length;
    }

    public void put(short[] array) {
        nodes.add(new ShortArrayNode(array, size));
        size += 8 + array.length * 2;
    }

    public void put(int[] array) {
        nodes.add(new IntArrayNode(array, size));
        size += 8 + array.length * 4;
    }

    public void put(float[] array) {
        nodes.add(new FloatArrayNode(array, size));
        size += 8 + array.length * 4;
    }

    public void put(double[] array) {
        nodes.add(new DoubleArrayNode(array, size));
        size += 8 + array.length * 8;
    }

    public void put(long[] array) {
        nodes.add(new LongArrayNode(array, size));
        size += 8 + array.length * 8;
    }

    public byte[] toBytes(){
        ByteBuffer buffer = ByteBuffer.allocate(size);
        nodes.forEach(i -> i.putToArrayBuffer(buffer));
        return buffer.array();
    }

    public void readByteBuffer(int bytesCount, ByteBuffer byteBuffer){
        int index = 0;

        ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        DoubleBuffer doubleBuffer = byteBuffer.asDoubleBuffer();
        LongBuffer longBuffer = byteBuffer.asLongBuffer();

        while (index < bytesCount) {
            int type = byteBuffer.get() - ArrayNode.ID;
            int size = byteBuffer.getInt();

            switch (type) {
                case ByteNode.ID: {
                    byte[] array = new byte[size];
                    nodes.add(new ByteArrayNode(array));
                    byteBuffer.get(array);
                    break;
                }
                case ShortNode.ID: {
                    short[] array = new short[size/2];
                    nodes.add(new ShortArrayNode(array));
                    shortBuffer.get(array);
                    break;
                }
                case IntNode.ID: {
                    int[] array = new int[size/4];
                    nodes.add(new IntArrayNode(array));
                    intBuffer.get(array);
                    break;
                }
                case FloatNode.ID: {
                    float[] array = new float[size/4];
                    nodes.add(new FloatArrayNode(array));
                    floatBuffer.get(array);
                    break;
                }
                case DoubleNode.ID: {
                    double[] array = new double[size/8];
                    nodes.add(new DoubleArrayNode(array));
                    doubleBuffer.get(array);
                    break;
                }
                case LongNode.ID: {
                    long[] array = new long[size/8];
                    nodes.add(new LongArrayNode(array));
                    longBuffer.get(array);
                    break;
                }
            }
            index += 5 + size;
        }
    }

    void clear(){
        nodes.clear();
        size = 0;
    }
}
