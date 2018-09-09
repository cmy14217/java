package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharSupplierTest {
    @Test
    void should_get_right_char() {
        CharSupplier charSupplier = () -> 'a';
        final int expectedChar = 'a';
        assertTrue(expectedChar == charSupplier.getAsChar());
    }

    @Test
    void should_return_what_input() {
        IntFunction intFunction = (a) -> a;
        assertTrue(1 == intFunction.apply(1));
    }

    @Test
    void should_return_add_a_and_b() {
        IntBiFunction intBiFunction = (a,b) -> a+b;
        assertTrue(3 == intBiFunction.apply(1,2));
    }

    @Test
    void should_swap_first_and_second_element_in_array_when_input_length_equals3() {
        Consumer consumer = returnLambda();
        Object[] initialObjects = new Object[]{1, 2, 3};
        consumer.accept(initialObjects);
        Object[] expectedObjects = new Object[]{2, 1, 3};
        assertArrayEquals(expectedObjects,initialObjects);
    }

    @Test
    void should_swap_first_and_second_element_in_array_when_input_length_equals1() {
        Consumer consumer = returnLambda();
        Object[] initialObjects = {1};
        consumer.accept(initialObjects);
        Object[] expectedObjects = new Object[]{1};
        assertArrayEquals(expectedObjects,initialObjects);
    }

    private static Consumer returnLambda(){
        return objects -> {
            if (objects.length >= 2) {
                Object tempObject = objects[0];
                objects[0] = objects[1];
                objects[1] = tempObject;
            }
        };
    }

    @Test
    void should_return_0_when_array_is_null() {
        ArraySummerFunction summer = returnSummerLambda();
        int[] initialIntArray = new int[]{};
        int expextedSum = 0;
        assertTrue(expextedSum == summer.apply(initialIntArray));
    }

    @Test
    void should_return_value_of_first_element_when_array_length_1() {
        ArraySummerFunction summer = returnSummerLambda();
        int[] initialIntArray = new int[]{2};
        int expextedSum = 2;
        assertTrue(expextedSum == summer.apply(initialIntArray));
    }

    @Test
    void should_return_sum_of_elements_in_array_when_array_length_greater_than_1() {
        ArraySummerFunction summer = returnSummerLambda();
        int[] initialIntArray = new int[]{1,2,3};
        int expextedSum = 6;
        assertTrue(expextedSum == summer.apply(initialIntArray));
    }

    private static ArraySummerFunction returnSummerLambda(){
        return intArray -> {
            if(intArray.length == 0){
                return 0;
            }
            if(intArray.length == 1){
                return intArray[0];
            }
            int sum = 0;
            for (int intElement: intArray) {
                sum += intElement;
            }
            return sum;
        };
    }
}
