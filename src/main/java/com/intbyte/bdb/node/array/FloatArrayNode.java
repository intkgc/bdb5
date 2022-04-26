package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.FloatNode;

import java.nio.ByteBuffer;

public class FloatArrayNode extends ArrayNode {
    private final float[] array;

    public FloatArrayNode(float[] array, int pointer) {
        super(ArrayNode.ID + FloatNode.ID, pointer);
        this.array = array;
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.asFloatBuffer().put(array);
    }

    @Override
    protected int arraySize() {
        return array.length * 4;
    }
}
