package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.FloatNode;

import java.nio.ByteBuffer;

public class FloatArrayNode extends ArrayNode<float[]> {

    public FloatArrayNode(float[] array, int pointer) {
        super( pointer);
        this.array = array;
    }

    public FloatArrayNode(float[] array) {
        super(array);
    }
    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        for (float i : array) {
            buffer.putFloat(i);
        }
    }

    @Override
    public int sizeOfByteArray() {
        return array.length * 4;
    }

    @Override
    protected int getType() {
        return FloatNode.ID;
    }
}
