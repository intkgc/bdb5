package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.LongNode;

import java.nio.ByteBuffer;

public class LongArrayNode extends ArrayNode {
    private final long[] array;

    public LongArrayNode(long[] array, int pointer) {
        super(ArrayNode.ID + LongNode.ID, pointer);
        this.array = array;
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.asLongBuffer().put(array);
    }

    @Override
    protected int arraySize() {
        return array.length * 8;
    }
}