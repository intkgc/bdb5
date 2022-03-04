package com.intbyte.bdb.node;

import com.intbyte.bdb.Node;

import java.nio.ByteBuffer;

final public class FloatNode extends Node {
    public static final byte ID = 3;
    public float value;

    public FloatNode(float value){
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }

    @Override
    public byte getId() {
        return ID;
    }
}
