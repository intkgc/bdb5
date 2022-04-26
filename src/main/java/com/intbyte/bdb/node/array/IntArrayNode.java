package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.IntNode;

import java.nio.ByteBuffer;

public class IntArrayNode extends ArrayNode {
    private final int[] array;

    public IntArrayNode(int[] array, int pointer) {
        super(ArrayNode.ID + IntNode.ID, pointer);
        this.array = array;
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.asIntBuffer().put(array);
    }

    @Override
    protected int arraySize() {
        return array.length * 4;
    }
}
