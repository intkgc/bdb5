package com.intbyte.bdb.node.array;

import java.nio.ByteBuffer;

public abstract class ArrayNode<T> {
    public static final byte ID = 6;
    public T array;

    protected int pointer = 0;

    protected abstract void convertToByteArray(ByteBuffer buffer);
    protected abstract int arraySize();
    protected abstract int getType();

    public void putToArrayBuffer(ByteBuffer byteBuffer){
        byteBuffer.put((byte) getType());
        byteBuffer.putInt(arraySize());
        convertToByteArray(byteBuffer);
    }

    public ArrayNode(int pointer){
        this.pointer = pointer;
    }

    public ArrayNode(T array){
        this.array = array;
    }
}
