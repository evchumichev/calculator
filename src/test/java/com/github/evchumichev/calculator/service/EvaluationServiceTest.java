package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.Difference;
import com.github.evchumichev.calculator.operations.Division;
import com.github.evchumichev.calculator.operations.Multiply;
import com.github.evchumichev.calculator.operations.Sum;
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
        assertEquals(3.1, evaluationService.doIt(arrayList), 0.0000000000000001);
    }

    @Test
    public void shouldCorrectlyDifferenceTwoNumbers() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(2.1));
        arrayList.add(new Difference());
        arrayList.add(new InputNumber(2));
        assertEquals(0.1, evaluationService.doIt(arrayList), 0.000000000000001);
    }

    @Test
    public void shouldCorrectlyDivisionTwoNumbers() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(4));
        arrayList.add(new Division());
        arrayList.add(new InputNumber(2));
        assertEquals(2, evaluationService.doIt(arrayList), 0.0000000000000001);
    }

    @Test
    public void shouldCorrectlyMultiplyTwoNumbers() {
        EvaluationService evaluationService = new EvaluationService();
        ArrayList<InputPart> arrayList = new ArrayList<>();
        arrayList.add(new InputNumber(1));
        arrayList.add(new Multiply());
        arrayList.add(new InputNumber(2));
        assertEquals(2, evaluationService.doIt(arrayList), 0.0000000000000001);
    }


}