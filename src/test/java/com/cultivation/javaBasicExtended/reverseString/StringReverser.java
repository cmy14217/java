package com.cultivation.javaBasicExtended.reverseString;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (input == null){
            throw new IllegalArgumentException();
        }
        String[] inputArray = input.split(" ");
        if (inputArray.length == 1 && inputArray[0].equals("")){
            inputArray = new String[0];
        }
        int length = inputArray.length;
        for (int count = 0; count < length / 2; count++) {
            String temp = inputArray[count];
            inputArray[count] = inputArray[length-1-count];
            inputArray[length-1-count] = temp;
        }
        return inputArray;
        // --end-->
    }
}
