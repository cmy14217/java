package com.cultivation.javaBasic.util;

public class MyInteger implements Comparable{

    private int number;

    public MyInteger(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Object o) {
        MyInteger newInteger = (MyInteger)o;
        return newInteger.number == this.number ? 0 : (newInteger.number > this.number ? -1 : 1);
    }
}
