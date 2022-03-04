package com.intbyte.bdb.node;

import com.intbyte.bdb.Node;

import java.nio.ByteBuffer;

final public class DoubleNode extends Node {
    public static final byte ID = 2;
    public double value;

    public DoubleNode(double value){
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return ByteBuffer.allocate(8).putDouble(value).array();
    }

    @Override
    public byte getId() {
        return ID;
    }
}
