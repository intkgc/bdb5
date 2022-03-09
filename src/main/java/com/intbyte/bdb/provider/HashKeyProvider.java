package com.intbyte.bdb.provider;

import com.intbyte.bdb.Key;
import com.intbyte.bdb.KeyProvider;

import java.util.Arrays;

public class HashKeyProvider implements KeyProvider {
    @Override
    public Key generateKey(String key) {
        return new HashKey(key.hashCode());
    }

    @Override
    public boolean compareKeys(Key key, Key secondKey) {
        return Arrays.equals(key.toBytes(), secondKey.toBytes());
    }

    @Override
    public Key createKey(byte[] bytes) {
        return new HashKey(bytes);
    }
}

