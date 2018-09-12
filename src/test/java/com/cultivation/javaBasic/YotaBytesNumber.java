package com.cultivation.javaBasic;

import java.util.Iterator;

public class YotaBytesNumber implements Iterable<Double> {
    private Double start;
    private Double end;

    public YotaBytesNumber(Double start, Double end) {
        if (start >= end) { throw new IllegalArgumentException("Start must be smaller than End."); }
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator iterator() {
        return new yotaNumberCalculator(start, end);
    }
}

class yotaNumberCalculator implements Iterator<Double>{

    private Double start;
    private Double end;
    private Double currentIndex;

    public yotaNumberCalculator(Double start, Double end) {
        this.start = start;
        this.end = end;
        currentIndex = start;
    }

    @Override
    public boolean hasNext() {
        return Math.pow(currentIndex, 10) < end;
    }

    @Override
    public Double next() {
        return currentIndex++;
    }
}
