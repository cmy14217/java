package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.EscapedChars;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharTypeTest {
    @Test
    void should_describe_escaped_chars() {
        // TODO: please modify the following code to pass the test
        // <--start
        final char backspace = 0x0008;
        final char tab = 0x0009;
        final char lineFeed = 0x000a;
        final char carriageReturn = 0x000d;
        final char doubleQuote = 0x0022;
        final char singleQuote = 0x0027;
        final char backslash = 0x005c;
        // --end-->

        assertEquals(EscapedChars.BACKSPACE.getValue(), backspace);
        assertEquals(EscapedChars.TAB.getValue(), tab);
        assertEquals(EscapedChars.LINE_FEED.getValue(), lineFeed);
        assertEquals(EscapedChars.CARRIAGE_RETURN.getValue(), carriageReturn);
        assertEquals(EscapedChars.DOUBLE_QUOTE.getValue(), doubleQuote);
        assertEquals(EscapedChars.SINGLE_QUOTE.getValue(), singleQuote);
        assertEquals(EscapedChars.BACKSLASH.getValue(), backslash);
    }

    /*
     * - Could a char represent one unicode character? Or, in other words, could a char represent a code point?
     * - How many bits are needed to represents one code point in UTF-16? What about UTF-8 and UTF-32?
     * - In Java, which encoding is used by char type? The UTF-16 encoding or UTF-8 encoding.
     * - Why there are many methods in Character class accepting an int parameter rather than char?
     */
}
