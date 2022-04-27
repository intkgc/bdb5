package com.intbyte.bdb;

import com.intbyte.bdb.provider.HashKeyProvider;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashDataBufferTest {
    DataBuffer buffer;

    {
        buffer = new DataBuffer(new HashKeyProvider());
        buffer.put("char", 'v');
        buffer.put("byte", (byte) 10);
        buffer.put("short", (short) 10);
        buffer.put("int", 10);
        buffer.put("float", 10.0f);
        buffer.put("double", 10.0);
        buffer.put("long", 10L);


        buffer.put("byteArray", new byte[]{7});
        buffer.put("shortArray", new short[]{7});
        buffer.put("intArray", new int[]{7});
        buffer.put("floatArray", new float[]{7});
        buffer.put("doubleArray", new double[]{7});
        buffer.put("longArray", new long[]{7});
    }



    @Test
    void contains() {
        assertTrue(buffer.contains("char"));
        assertTrue(buffer.contains("byte"));
        assertTrue(buffer.contains("short"));
        assertTrue(buffer.contains("int"));
        assertTrue(buffer.contains("float"));
        assertTrue(buffer.contains("double"));
        assertTrue(buffer.contains("long"));

        assertEquals(0, buffer.getInt("byteArray"));
    }

    @Test
    @Order(1)
    void toBytesAndReadBytes(){
        byte[] bytes = buffer.toBytes();
        buffer = new DataBuffer(new HashKeyProvider());
        buffer.readBytes(bytes);
        assertArrayEquals(bytes, buffer.toBytes());
    }

    @Test
    void getByte() {
        assertEquals(buffer.getByte("byte"), 10);
    }

    @Test
    void getShort() {
        assertEquals(buffer.getShort("short"), 10);
    }

    @Test
    void getChar() {
        assertEquals(buffer.getChar("char"), 'v');
    }

    @Test
    void getFloat() {
        assertEquals(buffer.getFloat("float"), 10f);
    }

    @Test
    void getDouble() {
        assertEquals(buffer.getDouble("double"), 10);
    }

    @Test
    void getInt() {
        assertEquals(buffer.getInt("int"), 10f);
    }

    @Test
    void getLong() {
        assertEquals(buffer.getLong("long"), 10);
    }

    @Test
    void getArray() {

        assertArrayEquals(buffer.getArray("byteArray"), new byte[]{7});
        assertArrayEquals(buffer.getArray("shortArray"), new short[]{7});
        assertArrayEquals(buffer.getArray("floatArray"), new float[]{7});
        assertArrayEquals(buffer.getArray("doubleArray"), new double[]{7});
        assertArrayEquals(buffer.getArray("intArray"), new int[]{7});
        assertArrayEquals(buffer.getArray("longArray"), new long[]{7});
    }
}