package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public InnerClassUpdateField() {
        year = 2018;
    }

    public InnerClassUpdateField(int year) {
        this.year = year;
    }

    public void add(){
        this.new UpdateField(2).add();
    }

    public void add(int year){
        this.new UpdateField(year).add();
    }

    public int getYear() {
        return year;
    }

    public class UpdateField{

        private int year;

        public UpdateField() {
        }

        public UpdateField(int year) {
            this.year = year;
            // InnerClassUpdateField.this.year += year;
        }

        public int getYear() {
            return year;
        }

        public void increasement(){
            year += 2;
        }

        public void add(){
            InnerClassUpdateField.this.year += this.year;
        }
    }
}
