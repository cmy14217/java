package com.cultivation.javaBasic.util;

public class LocalClassUpdateField {
    private int year;

    public LocalClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public void somethingHappen(){
        class Increasement{
            public void increase(){
                ++year;
            }
        }
        new Increasement().increase();
    }
}