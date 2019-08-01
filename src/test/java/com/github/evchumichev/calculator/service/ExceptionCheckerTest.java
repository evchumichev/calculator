package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.Difference;
import com.github.evchumichev.calculator.operations.Division;
import com.github.evchumichev.calculator.operations.Multiply;
import com.github.evchumichev.calculator.operations.SquareRoot;
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

    @Test (expected = RuntimeException.class)
    public void ShouldFindExceptionOneParamOperationAfterNumber() {
            ExceptionChecker exceptionChecker = new ExceptionChecker();
            ArrayList<InputPart> arrayList = new ArrayList<>();
            arrayList.add(new InputNumber(-4));
            arrayList.add(new SquareRoot());
            arrayList.add(new InputNumber(2));
            exceptionChecker.apply(arrayList);
    }


}
