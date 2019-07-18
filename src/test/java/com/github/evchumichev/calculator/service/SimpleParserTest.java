package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.Difference;
import com.github.evchumichev.calculator.operations.Division;
import com.github.evchumichev.calculator.operations.Multiply;
import com.github.evchumichev.calculator.operations.Sum;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class SimpleParserTest {
    @Test
    public void shouldCorrectlyParseStringSimpleSum() {
        String s = "52+34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(Sum.class, parts.get(1).getClass());
    }

    @Test
    public void shouldCorrectlyParseStringSimpleDifference() {
        String s = "52-34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(Difference.class, parts.get(1).getClass());
    }

    @Test
    public void shouldCorrectlyParseStringSimpleMultiply() {
        String s = "52*34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(Multiply.class, parts.get(1).getClass());
    }

    @Test
    public void shouldCorrectlyParseStringSimpleDivision() {
        String s = "52/34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(Division.class, parts.get(1).getClass());
    }

    @Test
    public void shouldCorrectlyParseStringTripleSum() {
        String s = "52+34-11";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(5, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(11.0, ((InputNumber) parts.get(4)).getNumber(), 0);
        assertEquals(Sum.class, parts.get(1).getClass());
        assertEquals(Difference.class, parts.get(3).getClass());
    }

    @Test(expected = RuntimeException.class)
    public void shouldCorrectlyParseStringDoubleMultiplyCharacter() {
        String s = "52**34";
        Parser parser = new SimpleParser();
        parser.parse(s);
    }

    @Test
    public void shouldCorrectlyParseStringNegativeNumber() {
        String s = "-52+34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(-52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(Sum.class, parts.get(1).getClass());
    }

    @Test(expected = RuntimeException.class)
    public void shouldCorrectlyParseStringPlusNumber() {
        String s = "+34";
        Parser parser = new SimpleParser();
        parser.parse(s);
    }

    @Test(expected = RuntimeException.class)
    public void shouldCorrectlyParseStringDoubleCharacter() {
        String s = "52/*34";
        Parser parser = new SimpleParser();
        parser.parse(s);
    }

    @Test
    public void shouldCorrectlyParseStringSingleNumber() {
        String s = "34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(1, parts.size());
        assertEquals(34.0, ((InputNumber) parts.get(0)).getNumber(), 0);
    }

    @Test
    public void shouldCorrectlyParseStringSingleNegativeNumber() {
        String s = "-34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(1, parts.size());
        assertEquals(-34.0, ((InputNumber) parts.get(0)).getNumber(), 0);
    }

    @Test
    public void shouldCorrectlyParseStringDoubleNegativeCharacter() {
        String s = "52--34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(-34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(Difference.class, parts.get(1).getClass());
    }

    @Test(expected = RuntimeException.class)
    public void shouldCorrectlyParseDifferenceCharacterInTheEnd() {
        String s = "87--34-";
        Parser parser = new SimpleParser();
        parser.parse(s);
    }

}