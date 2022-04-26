package com.intbyte.bdb;

import com.intbyte.bdb.provider.HashKeyProvider;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashDataBufferTest {
    DataBuffer buffer;

    {
        buffer = new DataBuffer(new HashKeyProvider());
        buffer.putChar("char", 'v');
        buffer.putByte("byte", (byte) 10);
        buffer.putShort("short", (short) 10);
        buffer.putInt("int", 10);
        buffer.putFloat("float", 10.0f);
        buffer.putDouble("double", 10.0);
        buffer.putLong("long", 10);
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
}