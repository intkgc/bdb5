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
    private final ArrayList<ArrayNode<?>> nodes = new ArrayList<>();
    private int size = 0;


    private int put(ArrayNode<?> node) {
        nodes.add(node);
        size += 8 + node.sizeOfByteArray();
        return nodes.size() - 1;
    }

    public int put(byte[] array) {
        return put(new ByteArrayNode(array, size));
    }

    public int put(short[] array) {
        return put(new ShortArrayNode(array, size));
    }

    public int put(int[] array) {
        return put(new IntArrayNode(array, size));
    }

    public int put(float[] array) {
        return put(new FloatArrayNode(array, size));
    }

    public int put(double[] array) {
        return put(new DoubleArrayNode(array, size));
    }

    public int put(long[] array) {
        return put(new LongArrayNode(array, size));
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(size);
        nodes.forEach(i -> i.putToArrayBuffer(buffer));
        return buffer.array();
    }

    public void readByteBuffer(int bytesCount, ByteBuffer byteBuffer) {
        int index = 0;


        while (index < bytesCount) {
            int type = byteBuffer.getInt();
            int size = byteBuffer.getInt();

            switch (type) {
                case ByteNode.ID: {
                    byte[] array = new byte[size];
                    byteBuffer.get(array);
                    put(array);
                    break;
                }
                case ShortNode.ID: {
                    short[] array = new short[size / 2];
                    byteBuffer.asShortBuffer().get(array);
                    put(array);
                    break;
                }
                case IntNode.ID: {
                    int[] array = new int[size / 4];
                    byteBuffer.asIntBuffer().get(array);
                    put(array);
                    break;
                }
                case FloatNode.ID: {
                    float[] array = new float[size / 4];
                    byteBuffer.asFloatBuffer().get(array);
                    put(array);
                    break;
                }
                case DoubleNode.ID: {
                    double[] array = new double[size / 8];
                    byteBuffer.asDoubleBuffer().get(array);
                    put(array);
                    break;
                }
                case LongNode.ID: {
                    long[] array = new long[size / 8];
                    byteBuffer.asLongBuffer().get(array);
                    put(array);
                    break;
                }
            }
            index += 8 + size;
        }
    }

    void clear() {
        nodes.clear();
        size = 0;
    }

    public ArrayNode<?> getNode(int index) {
        return nodes.get(index);
    }
}
