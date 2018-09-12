package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YotaBytesNumberTest {

    @Test
    void get_number_of_yotabyte_numbers_under_2_40() {
        YotaBytesNumber yotaBytesNumber = new YotaBytesNumber(1.0, Math.pow(2,40));
        int resultCount = 0;
        for (Double element : yotaBytesNumber) {
            resultCount++;
        }
        assertEquals(15, resultCount);
    }
}
