package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.domain.LeftParenthesis;
import com.github.evchumichev.calculator.domain.RightParenthesis;
import com.github.evchumichev.calculator.operations.OneParamOperation;
import com.github.evchumichev.calculator.operations.Operation;
import com.github.evchumichev.calculator.operations.TwoParamOperation;

import java.util.List;

public class ExceptionChecker {
    public void apply(List<InputPart> parts) {
        int parenthesisFlag = 0;
        if (parts.isEmpty())
            throw new RuntimeException("Your input function is empty!\nPlease, insert correct input function!");
        if (parts.get(0) instanceof TwoParamOperation || parts.get(parts.size() - 1) instanceof Operation)
            throw new RuntimeException("Incorrect format of input function!");
        for (int i = 0; i <= parts.size() - 1; i++) {
            if (parts.get(i) instanceof LeftParenthesis)
                parenthesisFlag++;
            if (parts.get(i) instanceof RightParenthesis) {
                if (i > 0 && parts.get(i - 1) instanceof LeftParenthesis)
                    throw new RuntimeException("Incorrect format of input function!\nSomething wrong with parenthesis order!");
                parenthesisFlag--;
            }
            if (parenthesisFlag < 0)
                throw new RuntimeException("Incorrect format of input function!\nSomething wrong with parenthesis order!");
            if (parts.get(i) instanceof TwoParamOperation) {
                if ((parts.get(i - 1) instanceof Operation) || parts.get(i + 1) instanceof TwoParamOperation || parts.get(i - 1) instanceof LeftParenthesis || parts.get(i + 1) instanceof RightParenthesis)
                    throw new RuntimeException("Incorrect format of input function!");
            }
            if (parts.get(i) instanceof OneParamOperation) {
                if ((i != 0 && parts.get(i - 1) instanceof InputNumber) || parts.get(i + 1) instanceof TwoParamOperation || parts.get(i + 1) instanceof RightParenthesis)
                    throw new RuntimeException("Incorrect format of input function!");
            }
            if (parts.get(i) instanceof InputNumber) {
                if ((i != 0 && parts.get(i - 1) instanceof InputNumber) || (i != parts.size() - 1) && parts.get(i + 1) instanceof InputNumber)
                    throw new RuntimeException("Incorrect format of input function!");
            }
        }
        if (parenthesisFlag != 0)
            throw new RuntimeException("Incorrect format of input function!\nSomething wrong with parenthesis amount!");
    }
}
