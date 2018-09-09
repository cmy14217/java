package com.cultivation.javaBasic.showYourIntelligence;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        StackTraceElement stackElementOfCurrentMethod = Thread.currentThread().getStackTrace()[2];
        return stackElementOfCurrentMethod.getClassName() + "." + stackElementOfCurrentMethod.getMethodName();
        // --end-->
    }
}
