package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.ShortNode;

import java.nio.ByteBuffer;


public class ShortArrayNode extends ArrayNode<short[]> {

    public ShortArrayNode(short[] array, int pointer) {
        super(pointer);
        this.array = array;
    }

    public ShortArrayNode(short[] array){
        super(array);
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.asShortBuffer().put(array);
    }

    @Override
    public int sizeOfByteArray() {
        return array.length*2;
    }

    @Override
    protected int getType() {
        return ShortNode.ID;
    }
}