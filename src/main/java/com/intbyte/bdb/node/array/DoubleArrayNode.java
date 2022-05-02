package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.DoubleNode;

import java.nio.ByteBuffer;

public class DoubleArrayNode extends ArrayNode<double[]> {

    public DoubleArrayNode(double[] array, int pointer) {
        super(pointer);
        this.array = array;
    }

    public DoubleArrayNode(double[] array) {
        super(array);
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        for (double i : array) {
            buffer.putDouble(i);
        }
    }

    @Override
    public int sizeOfByteArray() {
        return array.length * 8;
    }

    @Override
    protected int getType() {
        return DoubleNode.ID;
    }
}
