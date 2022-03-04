package com.intbyte.bdb.provider;

import com.intbyte.bdb.Key;

import java.nio.ByteBuffer;

class HashKey implements Key {

    private final byte[] hashKey;

    public HashKey(int hash) {
        hashKey = ByteBuffer.allocate(4).putInt(hash).array();
    }

    @Override
    public byte[] toBytes() {
        return hashKey;
    }
}
