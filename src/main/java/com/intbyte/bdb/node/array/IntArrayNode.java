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
        buffer.asIntBuffer().put(array);
    }

    @Override
    protected int arraySize() {
        return array.length * 4;
    }

    @Override
    protected int getType() {
        return ArrayNode.ID + IntNode.ID;
    }
}
