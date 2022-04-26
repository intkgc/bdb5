package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.ByteNode;

import java.nio.ByteBuffer;

public class ByteArrayNode extends ArrayNode {
    private byte[] array;

    public ByteArrayNode(byte[] array, int pointer) {
        super(ArrayNode.ID + ByteNode.ID, pointer);
        this.array = array;
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.put(array);
    }

    @Override
    protected int arraySize() {
        return array.length;
    }
}
