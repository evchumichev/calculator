package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.OneParamOperation;
import com.github.evchumichev.calculator.operations.Operation;
import com.github.evchumichev.calculator.operations.TwoParamOperation;

import java.util.List;

public class EvaluationService {
    public double evaluate(List<InputPart> parts) {
        while (parts.size() > 1) {
            int position = 0;
            int priority = 0;
            for (int i = 0; i < parts.size(); i++) {
                if (parts.get(i) instanceof Operation ) {
                    if (((Operation) parts.get(i)).getPriority() >= priority) {
                        position = i;
                        priority = ((Operation) parts.get(i)).getPriority();
                    }
                }
            }
            if (parts.get(position) instanceof OneParamOperation) {
                handleOneParamOperation(parts, position);
            }
            if (parts.get(position) instanceof TwoParamOperation) {
                handleTwoParamOperation(parts, position);
            }

        }
        return  ((InputNumber) parts.get(0)).getNumber();
    }

    private void handleTwoParamOperation(List<InputPart> parts, int position) {
        double returnValue = ((TwoParamOperation) parts.get(position)).apply(((InputNumber)parts.get(position - 1)).getNumber(), ((InputNumber)parts.get(position + 1)).getNumber());
        parts.remove(position + 1);
        parts.remove(position);
        parts.remove(position - 1);
        parts.add(position - 1, new InputNumber(returnValue));
    }

    private void handleOneParamOperation(List<InputPart> parts, int position) {
        double returnValue = ((OneParamOperation) parts.get(position)).apply(((InputNumber)parts.get(position + 1)).getNumber());
        parts.remove(position + 1);
        parts.remove(position);
        parts.add(position, new InputNumber(returnValue));
    }
}
