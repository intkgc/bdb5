package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.DoubleNode;

import java.nio.ByteBuffer;

public class DoubleArrayNode extends ArrayNode {
    private final double[] array;

    public DoubleArrayNode(double[] array, int pointer) {
        super(ArrayNode.ID + DoubleNode.ID, pointer);
        this.array = array;
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.asDoubleBuffer().put(array);
    }

    @Override
    protected int arraySize() {
        return array.length * 8;
    }
}
