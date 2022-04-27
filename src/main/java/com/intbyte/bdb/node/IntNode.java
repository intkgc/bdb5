package com.intbyte.bdb.node;

import com.intbyte.bdb.Node;

import java.nio.ByteBuffer;

final public class IntNode extends Node {
    public static final byte ID = 4;
    public int value;

    public IntNode(int value) {
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    @Override
    public byte getId() {
        return ID;
    }

    @Override
    public int getSize() {
        return 4;
    }
}
