package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.List;

public class Segment {
    private String segmentContent;
    private List<Integer> lineNumber;

    public Segment(String segmentContent, List<Integer> lineNumber) {
        this.segmentContent = segmentContent;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(segmentContent + "(");
        for (int i = 0; i < lineNumber.size() - 1; i++) {
            builder.append(lineNumber.get(i).toString() + ",");
        }
        builder.append(lineNumber.get(lineNumber.size()-1) + ");");

        return builder.toString();
    }
}
