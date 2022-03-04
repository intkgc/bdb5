package com.intbyte.bdb.node;

import com.intbyte.bdb.Node;

final public class ByteNode extends Node {
    public static final byte ID = 0;
    public byte value;

    public ByteNode(byte value){
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        return new byte[] {value};
    }

    @Override
    public byte getId() {
        return ID;
    }

    @Override
    public int getSize() {
        return 1;
    }
}
