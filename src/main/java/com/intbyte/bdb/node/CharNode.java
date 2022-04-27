package com.intbyte.bdb.node;

import com.intbyte.bdb.Node;

import java.nio.ByteBuffer;

final public class CharNode extends Node {
    public static final byte ID = 1;
    public char value;

    public CharNode(char value) {
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return ByteBuffer.allocate(2).putChar(value).array();
    }

    @Override
    public byte getId() {
        return ID;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
