package com.intbyte.bdb;

import com.intbyte.bdb.node.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

final public class DataBuffer {
    private final KeyProvider provider;
    private final ArrayList<Node> nodes;
    private int byteArraySize;

    public DataBuffer(KeyProvider keyProvider){
        this.provider = keyProvider;
        this.nodes = new ArrayList<>();
    }

    private void put(String key, @NotNull Node node){
        node.key = provider.generateKey(key);
        nodes.add(node);
    }
    public void putChar(String key, char value){
        put(key, new CharNode(value));
    }

    public void putByte(String key, byte value){
        put(key, new ByteNode(value));
    }

    public void putShort(String key, short value){
        put(key, new ShortNode(value));
    }

    public void putInt(String key, int value){
        put(key, new IntNode(value));
    }

    public void putFloat(String key, float value){
        put(key, new FloatNode(value));
    }

    public void putDouble(String key, float value){
        put(key, new DoubleNode(value));
    }

    public void putLong(String key, long value){
        put(key, new LongNode(value));
    }

    public byte[] toBytes(){
        return new byte[byteArraySize];
    }
}
