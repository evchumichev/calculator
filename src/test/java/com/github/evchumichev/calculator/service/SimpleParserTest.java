package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.*;
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

    @Test
    public void shouldCorrectlyParseStringDoubleMultiplyCharacter() {
        String s = "52**34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(4, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(3)).getNumber(), 0);
        assertEquals(Multiply.class, parts.get(1).getClass());
        assertEquals(Multiply.class, parts.get(2).getClass());
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

    @Test
    public void shouldCorrectlyParseStringPlusNumber() {
        String s = "+34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(2, parts.size());
        assertEquals(34.0, ((InputNumber) parts.get(1)).getNumber(), 0);
        assertEquals(Sum.class, parts.get(0).getClass());
    }

    @Test
    public void shouldCorrectlyParseStringDoubleCharacter() {
        String s = "52/*34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(4, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(3)).getNumber(), 0);
        assertEquals(Division.class, parts.get(1).getClass());
        assertEquals(Multiply.class, parts.get(2).getClass());

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

    @Test(expected = RuntimeException.class)
    public void shouldCorrectlyParseStringDifferenceAndOperation() {
        String s = "-*34";
        Parser parser = new SimpleParser();
        parser.parse(s);
        }

    @Test
    public void shouldCorrectlyParseSquareRoot() {
        String s = "sqrt8";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(2, parts.size());
        assertEquals(8, ((InputNumber) parts.get(1)).getNumber(), 0);
        assertEquals(SquareRoot.class, parts.get(0).getClass());
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

    @Test
    public void shouldCorrectlyParseDifferenceCharacterInTheEnd() {
        String s = "87--34-";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertEquals(4, parts.size());
        assertEquals(87.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(-34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(Difference.class, parts.get(1).getClass());
        assertEquals(Difference.class, parts.get(3).getClass());
    }

    @Test
    public void shouldCorrectlyParseSquareRootAndOperation() {
        String s = "52-sqrt34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(4, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(3)).getNumber(), 0);
        assertEquals(Difference.class, parts.get(1).getClass());
        assertEquals(SquareRoot.class, parts.get(2).getClass());
    }

    @Test
    public void shouldCorrectlyParseSquareRootWithoutOperation() {
        String s = "52sqrt34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(52.0, ((InputNumber) parts.get(0)).getNumber(), 0);
        assertEquals(34.0, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(SquareRoot.class, parts.get(1).getClass());
    }

    @Test
    public void shouldCorrectlyParseSquareRootNegativeNumber() {
        String s = "sqrt-34";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(2, parts.size());
        assertEquals(-34.0, ((InputNumber) parts.get(1)).getNumber(), 0);
        assertEquals(SquareRoot.class, parts.get(0).getClass());
    }

//    @Test
//    public void shouldCorrectlyParseMinusCharacter() {
//        String s = "-";
//        Parser parser = new SimpleParser();
//        List<InputPart> parts = parser.parse(s);
//        assertNotNull(parts);
//
//        assertEquals(1, parts.size());
//        assertEquals(Difference.class, parts.get(0).getClass());
//    }

    @Test
    public void shouldCorrectlyParseOneParamOperationAfterOneParamOperation() {
        String s = "sqrtsqrt2";
        Parser parser = new SimpleParser();
        List<InputPart> parts = parser.parse(s);
        assertNotNull(parts);

        assertEquals(3, parts.size());
        assertEquals(2, ((InputNumber) parts.get(2)).getNumber(), 0);
        assertEquals(SquareRoot.class, parts.get(0).getClass());
        assertEquals(SquareRoot.class, parts.get(1).getClass());
    }

//    @Test
//    public void shouldCorrectlyParseMinusCharacterSquareRoot() {
//        String s = "-sqrt54";
//        Parser parser = new SimpleParser();
//        List<InputPart> parts = parser.parse(s);
//        assertNotNull(parts);
//
//        assertEquals(3, parts.size());
//        assertEquals(54.0, ((InputNumber) parts.get(1)).getNumber(), 0);
//        assertEquals(SquareRoot.class, parts.get(1).getClass());
//        assertEquals(Difference.class, parts.get(0).getClass());
//    }


}