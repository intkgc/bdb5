package com.intbyte.bdb;

import com.intbyte.bdb.node.*;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

final public class DataBuffer {
    private final KeyProvider provider;
    private final ArrayList<Node> nodes;
    private int byteArraySize;
    private ArrayBuffer arrayBuffer = new ArrayBuffer();


    public DataBuffer(KeyProvider keyProvider) {
        this.provider = keyProvider;
        this.nodes = new ArrayList<>();
    }

    private void put(String key, @NotNull Node node) {
        node.key = provider.generateKey(key);
        byteArraySize += node.getSize() + node.key.toBytes().length + 1;
        nodes.add(node);
    }

    public void put(String key, char value) {
        put(key, new CharNode(value));
    }

    public void put(String key, byte value) {
        put(key, new ByteNode(value));
    }

    public void put(String key, short value) {
        put(key, new ShortNode(value));
    }

    public void put(String key, int value) {
        put(key, new IntNode(value));
    }

    public void put(String key, float value) {
        put(key, new FloatNode(value));
    }

    public void put(String key, double value) {
        put(key, new DoubleNode(value));
    }

    public void put(String key, long value) {
        put(key, new LongNode(value));
    }

    public void put(String key, byte[] array) {
        put(key, arrayBuffer.put(array));
    }

    public void put(String key, short[] array) {
        put(key, arrayBuffer.put(array));
    }

    public void put(String key, char[] array) {
        put(key, arrayBuffer.put(array));
    }

    public void put(String key, int[] array) {
        put(key, arrayBuffer.put(array));
    }

    public void put(String key, float[] array) {
        put(key, arrayBuffer.put(array));
    }

    public void put(String key, double[] array) {
        put(key, arrayBuffer.put(array));
    }

    public void put(String key, long[] array) {
        put(key, arrayBuffer.put(array));
    }

    public void put(String key, String value) {
        put(key, value.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] toBytes() {
        byte[] arrayBufferData = arrayBuffer.toBytes();
        byte[] bytes = new byte[byteArraySize + arrayBufferData.length + 4];
        ByteBuffer.wrap(bytes).putInt(arrayBufferData.length);
        System.arraycopy(arrayBufferData, 0, bytes, 4, arrayBufferData.length);

        int index = arrayBufferData.length + 4;
        for (Node node : nodes) {
            int length = node.key.toBytes().length;
            bytes[index++] = node.getId();
            System.arraycopy(node.key.toBytes(), 0, bytes, index, length);
            index += length;
            System.arraycopy(node.toBytes(), 0, bytes, index, node.toBytes().length);
            index += node.toBytes().length;
        }
        return bytes;
    }


    public void clear() {
        byteArraySize = 0;
        nodes.clear();
        arrayBuffer.clear();
    }

    public void readBytes(byte[] bytes) {
        byte[] byteKey = new byte[4];
        byte[] value = new byte[8];

        clear();

        ByteBuffer arrayByteBuffer = ByteBuffer.wrap(bytes);
        int arrayBufferSize = arrayByteBuffer.getInt();
        arrayBuffer.readByteBuffer(arrayBufferSize, arrayByteBuffer);

        int index = 4 + arrayBufferSize;

        while (index < bytes.length) {
            int id = bytes[index++];
            System.arraycopy(bytes, index, byteKey, 0, 4);
            Key key = provider.createKey(byteKey);
            index += 4;
            Node node;

            switch (id) {
                case ByteNode.ID:
                    node = new ByteNode(bytes[index++]);
                    break;
                case ShortNode.ID:
                    System.arraycopy(bytes, index, value, 0, 2);
                    node = new ShortNode(ByteBuffer.wrap(value).getShort(0));
                    index += 2;
                    break;
                case CharNode.ID:
                    System.arraycopy(bytes, index, value, 0, 2);
                    node = new CharNode(ByteBuffer.wrap(value).getChar(0));
                    index += 2;
                    break;
                case IntNode.ID:
                    System.arraycopy(bytes, index, value, 0, 4);
                    node = new IntNode(ByteBuffer.wrap(value).getInt(0));
                    index += 4;
                    break;
                case FloatNode.ID:
                    System.arraycopy(bytes, index, value, 0, 4);
                    node = new FloatNode(ByteBuffer.wrap(value).getFloat(0));
                    index += 4;
                    break;
                case DoubleNode.ID:
                    System.arraycopy(bytes, index, value, 0, 8);
                    node = new DoubleNode(ByteBuffer.wrap(value).getDouble(0));
                    index += 8;
                    break;
                case LongNode.ID:
                    System.arraycopy(bytes, index, value, 0, 8);
                    node = new LongNode(ByteBuffer.wrap(value).getLong(0));
                    index += 8;
                    break;
                default:
                    throw new IllegalStateException("unknown data type " + id);
            }

            node.key = key;
            byteArraySize += node.getSize() + node.key.toBytes().length + 1;
            nodes.add(node);
        }
    }

    private Node find(String key) {
        Key nodeKey = provider.generateKey(key);
        return nodes.stream().filter(node -> provider.compareKeys(nodeKey, node.key)).findAny().get();
    }

    public boolean contains(String key) {
        Key nodeKey = provider.generateKey(key);
        return nodes.stream().anyMatch(node -> provider.compareKeys(nodeKey, node.key));
    }

    public byte getByte(String key) {
        return ((ByteNode) find(key)).value;
    }

    public short getShort(String key) {
        return ((ShortNode) find(key)).value;
    }

    public char getChar(String key) {
        return ((CharNode) find(key)).value;
    }

    public float getFloat(String key) {
        return ((FloatNode) find(key)).value;
    }

    public double getDouble(String key) {
        return ((DoubleNode) find(key)).value;
    }

    public int getInt(String key) {
        return ((IntNode) find(key)).value;
    }

    public long getLong(String key) {
        return ((LongNode) find(key)).value;
    }

    public <T> T getArray(String key){
        return (T) arrayBuffer.getNode(getInt(key)).array;
    }

    public String getString(String key) {
        byte[] bytes = getArray(key);
        return new String(bytes);
    }

    public void put(String name, DataBuffer buffer) {
        put(name, buffer.toBytes());
    }

    public DataBuffer getDataBuffer(String name) {
        return new DataBuffer(getArray(name));
    }





}
