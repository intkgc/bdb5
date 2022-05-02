package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.IntNode;

import java.nio.ByteBuffer;

public class IntArrayNode extends ArrayNode<int[]> {

    public IntArrayNode(int[] array, int pointer) {
        super( pointer);
        this.array = array;
    }

    public IntArrayNode(int[] array) {
        super(array);
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        for (int i : array) {
            buffer.putInt(i);
        }
    }

    @Override
    public int sizeOfByteArray() {
        return array.length * 4;
    }

    @Override
    protected int getType() {
        return IntNode.ID;
    }
}
