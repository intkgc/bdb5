package com.intbyte.bdb.node.array;

import java.nio.ByteBuffer;

public abstract class ArrayNode {
    public static final byte ID = 6;

    protected int type = 0;
    protected int pointer = 0;

    protected abstract void convertToByteArray(ByteBuffer buffer);
    protected abstract int arraySize();

    public void putToArrayBuffer(ByteBuffer byteBuffer){
        byteBuffer.putInt(type);
        byteBuffer.putInt(arraySize());
        convertToByteArray(byteBuffer);
    }

    public ArrayNode(int type, int pointer){
        this.type = type;
        this.pointer = pointer;
    }

    public int getType() {
        return type;
    }
}
