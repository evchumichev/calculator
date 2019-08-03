package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.domain.LeftParenthesis;
import com.github.evchumichev.calculator.domain.RightParenthesis;
import com.github.evchumichev.calculator.operations.*;
import org.junit.Test;

import java.util.ArrayList;


public class ExceptionCheckerTest {
    @Test
    public void ShouldNotFindExceptionTwoParamOperationOnly() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Division());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldNotFindExceptionOneParamOperationOnly() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldNotFindExceptionOneAndTwoParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Multiply());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionIsEmptyList() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionTwoParamOperationAtBeginOfList() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Division());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionTwoParamOperationAtEndOfList() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Division());
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldNotFindExceptionOneParamOperationAtBeginOfList() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Division());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionOneParamOperationAtEndOfList() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        arrayList.add(new SquareRoot());
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionTwoParamOperationAfterOneParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new SquareRoot());
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldNotFindExceptionOneParamOperationAfterTwoParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionNumberAfterNumber() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionTwoParamOperationAfterTwoParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new Division());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldFindExceptionOneParamOperationAfterOneParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new SquareRoot());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionOneParamOperationAfterNumber() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    //
    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionRightParenthesisAfterTwoParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Multiply());
        arrayList.add(new RightParenthesis());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionTwoParamOperationAfterLeftParenthesis() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new LeftParenthesis());
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionRightParenthesisAfterLeftParenthesis() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Multiply());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new RightParenthesis());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionRightParenthesisAfterOneParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(-4));
        arrayList.add(new SquareRoot());
        arrayList.add(new RightParenthesis());
        arrayList.add(new InputNumber(2));
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldNotFindExceptionLeftParenthesisAfterOneParamOperation() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Multiply());
        arrayList.add(new SquareRoot());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldNotFindExceptionOneParamOperationAfterLeftParenthesis() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Multiply());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldNotFindExceptionRightParenthesisAfterRightParenthesisAndLeftParenthesisAfterLeftParenthesis() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        arrayList.add(new RightParenthesis());
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionWrongNumberOfParenthesis() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Multiply());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionListStartsWithRightParenthesis() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new RightParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Multiply());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        arrayList.add(new RightParenthesis());
        exceptionChecker.apply(arrayList);
    }

    @Test(expected = RuntimeException.class)
    public void ShouldFindExceptionWrongOrderOfParenthesis() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(-4));
        arrayList.add(new RightParenthesis());
        arrayList.add(new RightParenthesis());
        arrayList.add(new RightParenthesis());
        arrayList.add(new Multiply());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        arrayList.add(new RightParenthesis());
        exceptionChecker.apply(arrayList);
    }

    @Test
    public void ShouldFindNot() {
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(2));
        arrayList.add(new Multiply());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(1));
        arrayList.add(new RightParenthesis());
        arrayList.add(new Multiply());
        arrayList.add(new SquareRoot());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(4));
        exceptionChecker.apply(arrayList);
    }


}
