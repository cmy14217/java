package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloatingTypeTest {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @Test
    void fromOneDataTypeToTheOther() {

        byte byteNum = 1;
        short byteToShort = byteNum;
        int byteToInt = byteNum;
        long byteToLong = byteNum;
        float byteToFloat = byteNum;
        double byteToDouble = byteNum;

        short shortNum = 11;
        byte shortToByte = (byte)shortNum;
        int shortToInt = shortNum;
        long shortToLong = shortNum;
        float shortToFloat = shortNum;
        double shortToDouble = shortNum;

        int intNum = 12;
        byte intToByte = (byte)intNum;
        short intToShort = (short)intNum;
        long intToLong = intNum;
        float intToFloat = intNum;
        double intToDoublie = intNum;

        long longNum = 232L;
        byte longToByte = (byte)longNum;
        short longToShort = (short)longNum;
        int longToInt = (int)longNum;
        float longToFloat = longNum;
        double longToDouble = longNum;

        float floatNum = 11.32f;
        byte floatToByte = (byte)floatNum;
        short floatToShort = (short)floatNum;
        int floatToInt = (int)floatNum;
        long floatToLong = (long)floatNum;
        double floatToDouble = floatNum;

        double doubleNum = 3.33d;
        byte doubleToByte = (byte)doubleNum;
        short doubleToShort = (short)doubleNum;
        int doubleToInt = (int)doubleNum;
        long doubleToLong = (long)doubleNum;
        float doubleToFloat = (float)doubleNum;

    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isNaN(realNumber);
    }

    @Test
    void testNANEquals() {
        assertEquals(Double.NaN, Double.NaN);
        assertFalse(Double.NaN == Double.NaN);
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
//        if(realNumber == Double.POSITIVE_INFINITY || realNumber == Double.NEGATIVE_INFINITY){
//            return true;
//        }
//        return false;
        return Double.isInfinite(realNumber);
    }

    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * - Can we compare XXX_INFINITY using == directly?
     */
}
