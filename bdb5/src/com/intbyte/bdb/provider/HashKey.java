package com.intbyte.bdb.provider;

import com.intbyte.bdb.Key;

import java.nio.ByteBuffer;

class HashKey implements Key {

    private final byte[] hashKey;

    public HashKey(int hash) {
        hashKey = ByteBuffer.allocate(4).putInt(hash).array();
    }

    public HashKey(byte[] bytes){
        if(bytes.length > 4)
            throw new IllegalArgumentException("Hash key size cannot be bigger than 4 bytes");
        byte[] key = new byte[4];
        System.arraycopy(bytes, 0, key, 0, 4);
        hashKey = key;
    }

    @Override
    public byte[] toBytes() {
        return hashKey;
    }
}
