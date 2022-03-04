package com.intbyte.bdb.node;

import com.intbyte.bdb.Node;

import java.nio.ByteBuffer;

final public class ShortNode extends Node {
    public static final byte ID = 6;
    public short value;

    public ShortNode(short value){
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return ByteBuffer.allocate(2).putShort(value).array();
    }

    @Override
    public byte getId() {
        return ID;
    }
}
