package com.intbyte.bdb.node.array;

import java.nio.ByteBuffer;

public abstract class ArrayNode<T> {
    public T array;

    protected int pointer = 0;

    protected abstract void convertToByteArray(ByteBuffer buffer);
    public abstract int sizeOfByteArray();
    protected abstract int getType();

    public void putToArrayBuffer(ByteBuffer byteBuffer){
        byteBuffer.putInt(getType());
        byteBuffer.putInt(sizeOfByteArray());
        convertToByteArray(byteBuffer);
        return;
    }

    public ArrayNode(int pointer){
        this.pointer = pointer;
    }

    public ArrayNode(T array){
        this.array = array;
    }
}
