package com.intbyte.bdb;

import com.intbyte.bdb.node.array.ArrayNode;
import com.intbyte.bdb.node.array.ByteArrayNode;
import com.intbyte.bdb.node.array.DoubleArrayNode;
import com.intbyte.bdb.node.array.FloatArrayNode;
import com.intbyte.bdb.node.array.IntArrayNode;
import com.intbyte.bdb.node.array.LongArrayNode;
import com.intbyte.bdb.node.array.ShortArrayNode;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ArrayBuffer {
    private int size = 0;

    ArrayList<ArrayNode> nodes = new ArrayList<>();

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
}
