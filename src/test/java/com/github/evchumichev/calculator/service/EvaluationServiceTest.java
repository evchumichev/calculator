package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.domain.LeftParenthesis;
import com.github.evchumichev.calculator.domain.RightParenthesis;
import com.github.evchumichev.calculator.operations.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class EvaluationServiceTest {
    @Test
    public void shouldCorrectlySumTwoNumbers() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(1.1));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(2));
        assertEquals(3.1, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyDifferenceTwoNumbers() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(2.1));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        assertEquals(0.1, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyDivisionTwoNumbers() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(4));
        arrayList.add(new Division());
        arrayList.add(new InputNumber(2));
        assertEquals(2, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyMultiplyTwoNumbers() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(1));
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        assertEquals(2, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyPositiveNumberExponentPositiveNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(2));
        arrayList.add(new Power());
        arrayList.add(new InputNumber(2));
        assertEquals(4, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyPositiveNumberExponentNegativeNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(2));
        arrayList.add(new Power());
        arrayList.add(new InputNumber(-2));
        assertEquals(0.25, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyNegativeNumberExponentPositiveNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-2));
        arrayList.add(new Power());
        arrayList.add(new InputNumber(2));
        assertEquals(4, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyNegativeNumberExponentNegativeNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-2));
        arrayList.add(new Power());
        arrayList.add(new InputNumber(-2));
        assertEquals(0.25, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySquareRootPositiveNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(2));
        assertEquals(1.4142135623730951, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySumAndDivision() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(1));
        assertEquals(4, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySumAndDivisionNegativeNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(-2));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(1));
        assertEquals(0, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySumAndMultiply() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        assertEquals(7, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyDifferenseAndDivisionNegativeNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(-4));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Division());
        arrayList.add(new InputNumber(2));
        assertEquals(-5, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlyPowerAndDivisionAndSquareRootAndSumAndDifferenceAndMultiplyNegativeNumber() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(2));
        arrayList.add(new Power());
        arrayList.add(new InputNumber(3));
        arrayList.add(new Division());
        arrayList.add(new SquareRoot());
        arrayList.add(new InputNumber(8));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(4));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(1));
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(-1));
        assertEquals(7.828427125, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySumAndMultiplyWithParenthesis() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        assertEquals(10, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySumAndMultiplyWithParenthesisOver() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(2));
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        assertEquals(7, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySumAndMultiplyWithMultipleParenthesis() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        assertEquals(10, evaluationService.evaluate(arrayList), 0.01);
    }

    @Test
    public void shouldCorrectlySumAndMultiplyWithMultipleParenthesisOver() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new LeftParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new LeftParenthesis());
        arrayList.add(new InputNumber(3));
        arrayList.add(new Sum());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        arrayList.add(new RightParenthesis());
        arrayList.add(new RightParenthesis());
        arrayList.add(new RightParenthesis());
        assertEquals(10, evaluationService.evaluate(arrayList), 0.01);
    }


//    @Test
//    public void shouldCorrectlyNegativeNumberExponentNegativeNumber() {
//        EvaluationService evaluationService = new EvaluationService();
//        ArrayList<InputPart> arrayList = new ArrayList<>();
//        arrayList.add(new InputNumber(-2));
//        arrayList.add(new Power());
//        arrayList.add(new InputNumber(-2));
//        assertEquals(0.25, evaluationService.evaluate(arrayList), 0.0000000000000001);
//    }
//
//    @Test
//    public void shouldCorrectlyNegativeNumberExponentNegativeNumber() {
//        EvaluationService evaluationService = new EvaluationService();
//        ArrayList<InputPart> arrayList = new ArrayList<>();
//        arrayList.add(new InputNumber(-2));
//        arrayList.add(new Power());
//        arrayList.add(new InputNumber(-2));
//        assertEquals(0.25, evaluationService.evaluate(arrayList), 0.0000000000000001);
//    }


}