package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.LongNode;

import java.nio.ByteBuffer;

public class LongArrayNode extends ArrayNode<long[]> {

    public LongArrayNode(long[] array, int pointer) {
        super(pointer);
        this.array = array;
    }

    public LongArrayNode(long[] array) {
        super(array);
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.asLongBuffer().put(array);
    }

    @Override
    public int sizeOfByteArray() {
        return array.length * 8;
    }

    @Override
    protected int getType() {
        return LongNode.ID;
    }
}