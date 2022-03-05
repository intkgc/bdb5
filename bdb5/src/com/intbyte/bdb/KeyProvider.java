package com.intbyte.bdb;

public interface KeyProvider {
    Key generateKey(String key);
    Key createKey(byte[] bytes);
    boolean compareKeys(Key key, Key secondKey);
}
