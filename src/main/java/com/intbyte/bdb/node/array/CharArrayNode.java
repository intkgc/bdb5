package com.intbyte.bdb.node.array;

import com.intbyte.bdb.node.CharNode;
import com.intbyte.bdb.node.ShortNode;

import java.nio.ByteBuffer;


public class CharArrayNode extends ArrayNode<char[]> {

    public CharArrayNode(char[] array, int pointer) {
        super(pointer);
        this.array = array;
    }

    public CharArrayNode(char[] array) {
        super(array);
    }

    @Override
    protected void convertToByteArray(ByteBuffer buffer) {
        for (char i: array)
            buffer.putChar(i);
    }

    @Override
    public int sizeOfByteArray() {
        return array.length * 2;
    }

    @Override
    protected int getType() {
        return CharNode.ID;
    }
}