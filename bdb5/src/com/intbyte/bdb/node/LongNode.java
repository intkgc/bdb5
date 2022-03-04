package com.intbyte.bdb.node;

import com.intbyte.bdb.Node;

import java.nio.ByteBuffer;

final public class LongNode extends Node {
    public static final byte ID = 5;
    public long value;

    public LongNode(long value){
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return ByteBuffer.allocate(8).putLong(value).array();
    }
    @Override
    public byte getId() {
        return ID;
    }

    @Override
    public int getSize() {
        return 8;
    }
}
