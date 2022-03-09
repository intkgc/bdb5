package com.intbyte.bdb;

public abstract class Node {
    Key key;
    public abstract byte[] toBytes();
    public abstract byte getId();
    public abstract int getSize();
}
