package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.ArrayList;
import java.util.List;

public class CharacterDistinguish {

    private char[] textChars;
    private List<Segment> segmentList;

    public CharacterDistinguish(char[] textChars) {
        this.textChars = textChars;
        segmentList = new ArrayList<>();
    }

    public int getContinousWordFromIndex(int index){
        boolean initialCharacterType = Character.isWhitespace(textChars[index]);
        while (index < textChars.length && Character.isWhitespace(textChars[index]) == initialCharacterType){
            index++;
        }
        return index;
    }

    public String charArrayToStringFromStartToEnd(int start, int end){
        String resultString = "";
        for (int count = start; count < end; count++) {
            resultString += Character.toString(textChars[count]);
        }
        return resultString;
    }

    public List<Integer> caculateLineNumber(int width, int start, int end){
        List<Integer> lineNumbers = new ArrayList<>();
        int lineOfStart = start / width + 1;
        int lineOfEnd = (end - 1) / width + 1;
        for (int i = lineOfStart; i <= lineOfEnd ; i++) {
            lineNumbers.add(i);
        }
        return lineNumbers;
    }

    public void getAllSegements(int width){
        int startIndex = 0;
        while(startIndex < textChars.length){
            int endIndex = getContinousWordFromIndex(startIndex);
            String oneSegment = charArrayToStringFromStartToEnd(startIndex, endIndex);
            segmentList.add(new Segment(oneSegment, caculateLineNumber(width, startIndex, endIndex)));
            startIndex = endIndex;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Segment segment : segmentList) {
            builder.append(segment.toString());
        }
        return builder.toString();
    }
}
