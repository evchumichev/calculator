package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.domain.LeftParenthesis;
import com.github.evchumichev.calculator.domain.RightParenthesis;
import com.github.evchumichev.calculator.operations.Multiply;
import com.github.evchumichev.calculator.operations.SquareRoot;
import com.github.evchumichev.calculator.operations.Sum;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ParenthesisMultiplyAppenderTest {
    @Test
    public void shouldDoNothing() {
        ParenthesisMultiplyAppender parenthesisMultiplyAppender = new ParenthesisMultiplyAppender();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(1));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(1));
        arrayList.add(new RightParenthesis());
        parenthesisMultiplyAppender.apply(arrayList);
        assertNotNull(arrayList);

        assertEquals(5, arrayList.size());
    }

    @Test
    public void shouldAppendMultiplyBeforeLeftAndAfterRightParenthesis() {
        ParenthesisMultiplyAppender parenthesisMultiplyAppender = new ParenthesisMultiplyAppender();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(1));
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(1));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(1));
        arrayList.add(new RightParenthesis());
        arrayList.add(new InputNumber(1));
        parenthesisMultiplyAppender.apply(arrayList);
        assertNotNull(arrayList);

        assertEquals(9, arrayList.size());
        assertEquals(Multiply.class, arrayList.get(1).getClass());
        assertEquals(Multiply.class, arrayList.get(7).getClass());
    }

    @Test
    public void shouldAppendMultiplyBetweenRightAndLeftParenthesis() {
        ParenthesisMultiplyAppender parenthesisMultiplyAppender = new ParenthesisMultiplyAppender();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(1));
        arrayList.add(new InputNumber(1));
        arrayList.add(new RightParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(1));
        arrayList.add(new RightParenthesis());
        parenthesisMultiplyAppender.apply(arrayList);
        assertNotNull(arrayList);

        assertEquals(9, arrayList.size());
        assertEquals(Multiply.class, arrayList.get(4).getClass());
    }

    @Test //2(3+1)sqrtsqrt4
    public void shouldAppend() {
        ParenthesisMultiplyAppender parenthesisMultiplyAppender = new ParenthesisMultiplyAppender();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(2));
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(1));
        arrayList.add(new RightParenthesis());
        arrayList.add(new SquareRoot());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(4));
        parenthesisMultiplyAppender.apply(arrayList);
        assertNotNull(arrayList);

        assertEquals(11, arrayList.size());
        assertEquals(Multiply.class, arrayList.get(1).getClass());
        assertEquals(Multiply.class, arrayList.get(7).getClass());
    }
}
