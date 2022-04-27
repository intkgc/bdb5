package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.ByteNode;

import java.nio.ByteBuffer;

public class ByteArrayNode extends ArrayNode<byte[]> { ;

    public ByteArrayNode(byte[] array, int pointer) {
        super(pointer);
        this.array = array;
    }

    public ByteArrayNode(byte[] array){
        super(array);
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        buffer.put(array);
    }

    @Override
    protected int arraySize() {
        return array.length;
    }

    @Override
    protected int getType() {
        return ArrayNode.ID + ByteNode.ID;
    }
}
