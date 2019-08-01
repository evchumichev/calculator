package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.*;
import com.github.evchumichev.calculator.operations.OneParamOperation;
import com.github.evchumichev.calculator.operations.Operation;
import com.github.evchumichev.calculator.operations.TwoParamOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EvaluationService { //((N+N)*N)
    public double evaluate(List<InputPart> parts) {
        Stack<Integer> parenthesisStack = new Stack<>();
        while (parts.size() > 1) {
            int position = 0;
            int priority = 0;
            boolean isHandled = false;
            for (int i = 0; i < parts.size(); i++) {
                if (parts.get(i) instanceof LeftParenthesis) {
                    parenthesisStack.push(i);
                    continue;
                }
                if (parts.get(i) instanceof RightParenthesis) {
                    handleParenthesis(parenthesisStack.pop(), i, parts);
                    isHandled = true;
                    break;
                }
                if (parts.get(i) instanceof Operation) {
                    if (((Operation) parts.get(i)).getPriority() >= priority) {
                        position = i;
                        priority = ((Operation) parts.get(i)).getPriority();
                    }
                }
            }
            if (isHandled)
                continue;
            if (parts.get(position) instanceof OneParamOperation) {
                handleOneParamOperation(parts, position);
            }
            if (parts.get(position) instanceof TwoParamOperation) {
                handleTwoParamOperation(parts, position);
            }

        }
        return ((InputNumber) parts.get(0)).getNumber();
    }
    private void handleParenthesis(int startPosition, int endPosition, List<InputPart> parts) {
        List<InputPart> parenthisisList = new ArrayList<InputPart>();
        for (int i = 0; i <= endPosition - startPosition; i++) {
            if ((parts.get(startPosition) instanceof InputNumber) || (parts.get(startPosition) instanceof Operation)) {
                parenthisisList.add(parts.get(startPosition));
            }
            parts.remove(startPosition);
        }
        parts.add(startPosition, new InputNumber(evaluate(parenthisisList)));

    }

    private void handleTwoParamOperation(List<InputPart> parts, int position) {
        double returnValue = ((TwoParamOperation) parts.get(position)).apply(((InputNumber) parts.get(position - 1)).getNumber(), ((InputNumber) parts.get(position + 1)).getNumber());
        parts.remove(position + 1);
        parts.remove(position);
        parts.remove(position - 1);
        parts.add(position - 1, new InputNumber(returnValue));
    }

    private void handleOneParamOperation(List<InputPart> parts, int position) {
        double returnValue = ((OneParamOperation) parts.get(position)).apply(((InputNumber) parts.get(position + 1)).getNumber());
        parts.remove(position + 1);
        parts.remove(position);
        parts.add(position, new InputNumber(returnValue));
    }

}
