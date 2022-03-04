package com.intbyte.bdb;

public interface KeyProvider {
    Key generateKey(String key);
    boolean compareKeys(Key key, Key secondKey);
}
